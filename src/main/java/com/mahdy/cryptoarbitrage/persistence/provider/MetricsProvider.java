package com.mahdy.cryptoarbitrage.persistence.provider;

import com.mahdy.cryptoarbitrage.core.model.enumeration.ExternalApi;

/**
 * @author Mehdi Kamali
 * @since 13/10/2025
 */
public interface MetricsProvider {

    void recordApiCallDuration(ExternalApi externalAPI, long durationMillis);

    void incrementApiCallCount(ExternalApi externalAPI, boolean success);
}
