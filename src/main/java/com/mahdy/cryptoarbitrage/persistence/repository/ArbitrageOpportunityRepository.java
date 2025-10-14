package com.mahdy.cryptoarbitrage.persistence.repository;

import com.mahdy.cryptoarbitrage.persistence.provider.entity.ArbitrageOpportunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Repository
public interface ArbitrageOpportunityRepository extends JpaRepository<ArbitrageOpportunityEntity, Long> {
}
