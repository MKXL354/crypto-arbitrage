package com.mahdy.cryptoarbitrage.invoker.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class WallexCoinPriceResponseQuotes {

    private BigDecimal price;
}
