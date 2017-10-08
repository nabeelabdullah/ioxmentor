package com.filedownload.filedownload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("")
public class FiledownloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiledownloadApplication.class, args);
    }
}
