package com.cryptohive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // Enable scheduling tasks in Spring Boot
public class CryptoHiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptoHiveApplication.class, args);
    }
}
