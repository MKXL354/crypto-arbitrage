package com.mahdy.cryptoarbitrage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableAspectJAutoProxy
public class CryptoArbitrageApp {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tehran"));
        SpringApplication.run(CryptoArbitrageApp.class, args);
    }
}
//TODO: uniform facade for calling exchange apis
//TODO: support more coins? as limited config and enum values
