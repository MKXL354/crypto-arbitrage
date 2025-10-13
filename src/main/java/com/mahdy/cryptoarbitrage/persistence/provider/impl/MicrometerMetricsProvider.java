package com.mahdy.cryptoarbitrage.persistence.provider.impl;

import com.mahdy.cryptoarbitrage.core.model.enumeration.ExternalApi;
import com.mahdy.cryptoarbitrage.persistence.provider.MetricsProvider;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Mehdi Kamali
 * @since 13/10/2025
 */
@Component
@RequiredArgsConstructor
public class MicrometerMetricsProvider implements MetricsProvider {

    private final MeterRegistry meterRegistry;

    private final AtomicReference<BigDecimal> arbitrageDifferenceSummary = new AtomicReference<>(BigDecimal.ZERO);

    private final String EXTERNAL_API_REQUEST_DURATION = "external_api_request_duration";
    private final String EXTERNAL_API_REQUESTS_TOTAL = "external_api_requests_total";
    private final String ARBITRAGE_OPPORTUNITIES_TOTAL = "arbitrage_opportunities_total";
    private final String ARBITRAGE_DIFFERENCE_PERCENT = "arbitrage_difference_percent";

    @PostConstruct
    public void init() {
        Gauge.builder(ARBITRAGE_DIFFERENCE_PERCENT, arbitrageDifferenceSummary::get)
                .baseUnit("percent")
                .register(meterRegistry);
    }

    @Override
    public void recordApiCallDuration(ExternalApi externalAPI, long durationMillis) {
        Timer timer = meterRegistry.timer(EXTERNAL_API_REQUEST_DURATION, "api", externalAPI.getName());
        timer.record(durationMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public void incrementApiCallsCount(ExternalApi externalAPI, boolean success) {
        Counter counter = meterRegistry.counter(
                EXTERNAL_API_REQUESTS_TOTAL,
                "api", externalAPI.getName(),
                "status", success ? "success" : "failure"
        );
        counter.increment();
    }

    @Override
    public void incrementArbitrageOpportunitiesCount() {
        Counter counter = meterRegistry.counter(ARBITRAGE_OPPORTUNITIES_TOTAL);
        counter.increment();
    }

    @Override
    public void updateArbitrageDifferencePercent(BigDecimal differencePercent) {
        arbitrageDifferenceSummary.set(differencePercent);
    }
}
