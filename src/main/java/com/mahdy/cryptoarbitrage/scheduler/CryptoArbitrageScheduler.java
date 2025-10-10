package com.mahdy.cryptoarbitrage.scheduler;

import com.mahdy.cryptoarbitrage.core.service.CryptoArbitrageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${scheduler.arbitrage.enabled}")
    private boolean enabled;

    @Scheduled(fixedDelayString = "${scheduler.arbitrage.interval-secs}", timeUnit = TimeUnit.SECONDS)
    public void run() {
        if (!enabled) {
            return;
        }
        log.info("CryptoArbitrageScheduler triggered at {}", LocalDateTime.now());
        cryptoArbitrageService.findArbitrageOpportunity();
    }
}
