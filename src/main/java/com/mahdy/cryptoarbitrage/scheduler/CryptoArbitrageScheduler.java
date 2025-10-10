package com.mahdy.cryptoarbitrage.scheduler;

import com.mahdy.cryptoarbitrage.core.service.CryptoArbitrageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CryptoArbitrageScheduler {

    private final CryptoArbitrageService cryptoArbitrageService;

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
//    TODO: move this timing to config
    public void run() {
        log.info("CryptoArbitrageScheduler triggered at {}", LocalDateTime.now());
        cryptoArbitrageService.findArbitrageOpportunity();
    }
}
