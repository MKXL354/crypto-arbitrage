package com.mahdy.cryptoarbitrage.persistence.assembler;

import com.mahdy.cryptoarbitrage.core.model.ArbitrageOpportunity;
import com.mahdy.cryptoarbitrage.persistence.provider.entity.ArbitrageOpportunityEntity;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Component
public class ArbitrageOpportunityAssembler {

    public ArbitrageOpportunityEntity toArbitrageOpportunityEntity(ArbitrageOpportunity arbitrageOpportunity) {
        ArbitrageOpportunityEntity entity = new ArbitrageOpportunityEntity();
        entity.setId(arbitrageOpportunity.getId());
        entity.setSrcCurrency(arbitrageOpportunity.getSrcCurrency());
        entity.setDstCurrency(arbitrageOpportunity.getDstCurrency());
        entity.setFindTime(arbitrageOpportunity.getFindTime());
        entity.setNobitexPrice(arbitrageOpportunity.getNobitexPrice());
        entity.setWallexPrice(arbitrageOpportunity.getWallexPrice());
        entity.setDifference(arbitrageOpportunity.getDifference());
        entity.setDifferencePercent(arbitrageOpportunity.getDifferencePercent());
        return entity;
    }
}
