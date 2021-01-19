package com.example.chengjubackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChengjuBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChengjuBackendApplication.class, args);
    }

}
