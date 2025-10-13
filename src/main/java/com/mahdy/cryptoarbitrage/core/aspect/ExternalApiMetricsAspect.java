package com.mahdy.cryptoarbitrage.core.aspect;

import com.mahdy.cryptoarbitrage.core.model.annotation.TrackExternalApiMetrics;
import com.mahdy.cryptoarbitrage.core.model.enumeration.ExternalApi;
import com.mahdy.cryptoarbitrage.persistence.provider.MetricsProvider;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 13/10/2025
 */
@Component
@Aspect
@RequiredArgsConstructor
public class ExternalApiMetricsAspect {

    private final MetricsProvider metricsProvider;

    @Around("@annotation(trackMetrics)")
    public Object aroundExternalApiCall(ProceedingJoinPoint pjp, TrackExternalApiMetrics trackMetrics) throws Throwable {
        ExternalApi api = trackMetrics.value();
        long start = System.currentTimeMillis();
        try {
            Object result = pjp.proceed();
            metricsProvider.incrementApiCallCount(api, true);
            return result;
        } catch (Exception e) {
            metricsProvider.incrementApiCallCount(api, false);
            throw e;
        } finally {
            long duration = System.currentTimeMillis() - start;
            metricsProvider.recordApiCallDuration(api, duration);
        }
    }
}
