package com.example.bookservice.service;

import com.example.bookservice.dto.lib.LibInfoResponseDto;
import com.example.bookservice.exception.CustomException;
import com.example.bookservice.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class LibService {
    @Value("${webclient.searchUrl}")
    private String SEARCH_URL;

    @Value("${webclient.statusUrlStart}")
    private String STATUS_URL_START;

    @Value("${webclient.statusUrlEnd}")
    private String STATUS_URL_END;

    @Value("${webclient.libSearchUrl}")
    private String LIB_SEARCH_URL;

    private final WebClient webClient;

    // 도서관 사이트 책 검색
    @Transactional
    public LibInfoResponseDto findInfo(String bookName) throws UnsupportedEncodingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // 책 이름으로 통합검색 (GET)
        URI infoUrl = URI.create(SEARCH_URL + URLEncoder.encode(bookName, "UTF-8"));
        log.info("[infoUrl] is " + infoUrl);

        String info = getAPI(infoUrl);

        // 응답 결과 중 필요한 내용만 파싱
        JsonNode infoJson = objectMapper.readTree(info);

        Long id = infoJson.path("data").path("list").path(0).path("id").asLong();
        String type = infoJson.path("data").path("list").path(0).path("biblioType").path("name").asText();
        String billNum = infoJson.path("data").path("list").path(0).path("branchVolumes").path(0).path("volume").asText();
        String thumbnailUrl = infoJson.path("data").path("list").path(0).path("thumbnailUrl").asText();

        String resultBookName = infoJson.path("data").path("list").path(0).path("titleStatement").asText();

        log.info("id : {}, resultBookName : {}, type : {}, billNum : {}, thumbnailUrl : {}", id, resultBookName, type, billNum, thumbnailUrl);

        // 책 이름 유사도 비교
        int distance = levenshteinDistance(bookName, resultBookName);
        double similar = 1.0 - ((double) distance / Math.max(bookName.length(), resultBookName.length()));
        log.info("[similar] 유사도 체크 완료 | 유사도 : {}", similar);

        // 유사도가 0.1 미만 404 에러 - 잘못된 검색 결과, 하지만 이렇게 해도 잘못된 결과 나올 수 있음
        if (similar < 0.1) {
            throw new CustomException(ErrorCode.BOOK_NOT_FOUND);
        }


        // 책 아이디로 도서관 책 상태 검색 (GET)
        URI statusUrl = URI.create(STATUS_URL_START + id + STATUS_URL_END);
        log.info("[statusUrl] is " + statusUrl);

        String status = getAPI(statusUrl);

        // 응답 결과 중 필요한 내용만 파싱
        JsonNode statusJson = objectMapper.readTree(status);
        int availableNum = 0;
        int entireNum = statusJson.path("data").path("totalCount").asInt();
        String location = "";

        Set<String> bookLocations = new HashSet<String>();

        for (int i = 0 ; i < entireNum ; i++) {
            String isAvailable = statusJson.path("data").path("list").path(i).path("circulationState").path("name").asText();
            String bookLocation = statusJson.path("data").path("list").path(i).path("location").path("name").asText();
            log.info("Book {}'s status : isAvailable - {} | bookLocation - {}", i, isAvailable, bookLocation);

            bookLocations.add(bookLocation);

            if (isAvailable.equals("대출가능")) {
                availableNum += 1;
            }
        }
        location = bookLocations.toString().replaceAll("[\\[\\]]", "");

        log.info(location);
        log.info("availableNum / entireNum : {} / {}", availableNum, entireNum);

        LibInfoResponseDto libInfoResponseDto = LibInfoResponseDto.builder()
                .type(type)
                .billNum(billNum)
                .location(location)
                .thumbnailUrl(thumbnailUrl)
                .availableNum(availableNum)
                .entireNum(entireNum)
                .bookUrl(LIB_SEARCH_URL + id)
                .build();

        return libInfoResponseDto;
    }

    // 유사도 검사 알고리즘
    public int levenshteinDistance(String originBookName, String resultBookName) {
        int m = originBookName.length();
        int n = resultBookName.length();
        int[][] d = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            d[0][j] = j;
        }

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (originBookName.charAt(i - 1) == resultBookName.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + 1);
                }
            }
        }

        return d[m][n];
    }

    // 외부 API 호출 (GET)
    public String getAPI(URI url) {
        String response = webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}
