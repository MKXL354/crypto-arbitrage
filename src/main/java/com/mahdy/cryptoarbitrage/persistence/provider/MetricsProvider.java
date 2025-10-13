package com.mahdy.cryptoarbitrage.persistence.provider;

import com.mahdy.cryptoarbitrage.core.model.enumeration.ExternalApi;

import java.math.BigDecimal;

/**
 * @author Mehdi Kamali
 * @since 13/10/2025
 */
public interface MetricsProvider {

    void recordApiCallDuration(ExternalApi externalAPI, long durationMillis);

    void incrementApiCallsCount(ExternalApi externalAPI, boolean success);

    void incrementArbitrageOpportunitiesCount();

    void updateArbitrageDifferencePercent(BigDecimal differencePercent);
}
