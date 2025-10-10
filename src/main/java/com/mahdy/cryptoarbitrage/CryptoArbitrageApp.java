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
//TODO: more logging? facade request/response, external request/response, scheduler
//TODO: server (port), bot token, feign client (url) config
//TODO: is error handling even needed? (only for registerUpdate in API?)
