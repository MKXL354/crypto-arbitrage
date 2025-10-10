package com.mahdy.cryptoarbitrage.invoker.dto.response;

import lombok.Data;

import java.util.Map;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class NobitexMarketStatsResponse {

    private String status;
    private Map<String, NobitexMarketResponseStats> stats;
}
