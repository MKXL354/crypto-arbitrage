package com.mahdy.cryptoarbitrage.invoker.dto.response;

import lombok.Data;

import java.util.Map;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class WallexCoinPriceResponseMarkets {

    private Map<String, WallexCoinPriceResponseQuotes> quotes;
}
