package com.mahdy.cryptoarbitrage.persistence.provider.impl;

import com.mahdy.cryptoarbitrage.core.model.ArbitrageOpportunity;
import com.mahdy.cryptoarbitrage.persistence.assembler.ArbitrageOpportunityAssembler;
import com.mahdy.cryptoarbitrage.persistence.provider.ArbitrageOpportunityProvider;
import com.mahdy.cryptoarbitrage.persistence.provider.entity.ArbitrageOpportunityEntity;
import com.mahdy.cryptoarbitrage.persistence.repository.ArbitrageOpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Component
@RequiredArgsConstructor
public class ArbitrageOpportunityProviderImpl implements ArbitrageOpportunityProvider {

    private final ArbitrageOpportunityRepository arbitrageOpportunityRepository;
    private final ArbitrageOpportunityAssembler arbitrageOpportunityAssembler;

    @Override
    public ArbitrageOpportunity save(ArbitrageOpportunity arbitrageOpportunity) {
        ArbitrageOpportunityEntity entity = arbitrageOpportunityAssembler.toArbitrageOpportunityEntity(arbitrageOpportunity);
        entity = arbitrageOpportunityRepository.save(entity);
        arbitrageOpportunity.setId(entity.getId());
        return arbitrageOpportunity;
    }
}
