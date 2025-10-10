package com.mahdy.cryptoarbitrage.invoker.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class WallexCoinPriceResponseResult {

    private List<WallexCoinPriceResponseMarkets> markets;
}
