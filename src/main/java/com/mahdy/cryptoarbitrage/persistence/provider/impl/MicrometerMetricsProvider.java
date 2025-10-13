package com.mahdy.cryptoarbitrage.persistence.provider.impl;

import com.mahdy.cryptoarbitrage.core.model.enumeration.ExternalApi;
import com.mahdy.cryptoarbitrage.persistence.provider.MetricsProvider;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Mehdi Kamali
 * @since 13/10/2025
 */
@Component
@RequiredArgsConstructor
public class MicrometerMetricsProvider implements MetricsProvider {

    private final MeterRegistry meterRegistry;

    private final String EXTERNAL_API_REQUEST_DURATION = "external_api_request_duration";
    private final String EXTERNAL_API_REQUESTS_TOTAL = "external_api_requests_total";

    @Override
    public void recordApiCallDuration(ExternalApi externalAPI, long durationMillis) {
        Timer timer = meterRegistry.timer(EXTERNAL_API_REQUEST_DURATION, "api", externalAPI.getName());
        timer.record(durationMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public void incrementApiCallCount(ExternalApi externalAPI, boolean success) {
        Counter counter = meterRegistry.counter(
                EXTERNAL_API_REQUESTS_TOTAL,
                "api", externalAPI.getName(),
                "status", success ? "success" : "failure"
        );
        counter.increment();
    }
}
