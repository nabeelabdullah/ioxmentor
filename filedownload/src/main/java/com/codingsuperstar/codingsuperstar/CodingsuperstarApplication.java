package com.codingsuperstar.codingsuperstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.codingsuperstar")
@EnableJpaRepositories("")
public class CodingsuperstarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingsuperstarApplication.class, args);
    }
}
