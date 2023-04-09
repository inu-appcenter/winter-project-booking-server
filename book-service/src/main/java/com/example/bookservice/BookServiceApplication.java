package com.example.bookservice;

import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.repository.SubjectRepository;
import com.example.bookservice.service.TestDataInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.CharacterEncodingFilter;


@SpringBootApplication
@EnableJpaAuditing
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	@Profile("local")
	public TestDataInit testDataInit(SubjectRepository subjectRepository, BookRepository bookRepository) {
		return new TestDataInit(subjectRepository, bookRepository);
	}
}