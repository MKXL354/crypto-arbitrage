package com.mahdy.cryptoarbitrage.persistence.provider;

import com.mahdy.cryptoarbitrage.core.model.ArbitrageOpportunity;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
public interface ArbitrageOpportunityProvider {

    ArbitrageOpportunity save(ArbitrageOpportunity arbitrageOpportunity);
}
