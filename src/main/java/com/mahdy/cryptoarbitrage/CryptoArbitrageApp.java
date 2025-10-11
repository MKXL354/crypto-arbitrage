package com.mahdy.cryptoarbitrage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class CryptoArbitrageApp {

    public static void main(String[] args) {
        SpringApplication.run(CryptoArbitrageApp.class, args);
    }
}
