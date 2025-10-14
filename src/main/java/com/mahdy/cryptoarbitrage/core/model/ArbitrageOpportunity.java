package com.mahdy.cryptoarbitrage.core.model;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Data
public class ArbitrageOpportunity {

    private long id;
    private Currency srcCurrency;
    private Currency dstCurrency;
    private LocalDateTime findTime;
    private BigDecimal nobitexPrice;
    private BigDecimal wallexPrice;
    private BigDecimal difference;
    private BigDecimal differencePercent;
}
