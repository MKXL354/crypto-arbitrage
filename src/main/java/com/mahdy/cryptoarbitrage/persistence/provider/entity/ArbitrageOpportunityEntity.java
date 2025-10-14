package com.mahdy.cryptoarbitrage.persistence.provider.entity;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Entity
@Table(name = "ARBITRAGE_OPPORTUNITY")
@Getter
@Setter
public class ArbitrageOpportunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "SRC_CURRENCY", columnDefinition = "VARCHAR(10)", nullable = false)
    private Currency srcCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "DST_CURRENCY", columnDefinition = "VARCHAR(10)", nullable = false)
    private Currency dstCurrency;

    @Column(name = "FIND_TIME", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime findTime;

    @Column(name = "NOBITEX_PRICE", columnDefinition = "NUMERIC", nullable = false)
    private BigDecimal nobitexPrice;

    @Column(name = "WALLEX_PRICE", columnDefinition = "NUMERIC", nullable = false)
    private BigDecimal wallexPrice;

    @Column(name = "DIFFERENCE", columnDefinition = "NUMERIC", nullable = false)
    private BigDecimal difference;

    @Column(name = "DIFFERENCE_PERCENT", columnDefinition = "NUMERIC", nullable = false)
    private BigDecimal differencePercent;
}
