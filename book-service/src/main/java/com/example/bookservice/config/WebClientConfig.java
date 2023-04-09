package com.example.bookservice.config;

import com.google.common.net.HttpHeaders;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                .doOnConnected(conn ->
                        conn.addHandlerFirst(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerFirst(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));


        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector((httpClient)))
                .build();
    }
}
