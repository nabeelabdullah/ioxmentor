package com.ioxmentor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAutoConfiguration
@Component("com.ioxmentor")
@EnableJpaRepositories()
public class IoxmentorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoxmentorApplication.class, args);
	}
}
