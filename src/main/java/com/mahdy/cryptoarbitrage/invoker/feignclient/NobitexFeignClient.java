package com.mahdy.cryptoarbitrage.invoker.feignclient;

import com.mahdy.cryptoarbitrage.invoker.dto.response.NobitexMarketStatsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@FeignClient(name = "nobitex", url = "${external.nobitex.base-api-url}")
public interface NobitexFeignClient {

    String MARKET_STATS = "/market/stats";

    @GetMapping(MARKET_STATS)
    NobitexMarketStatsResponse getMarketStats(@RequestParam String srcCurrency, @RequestParam String dstCurrency);
}
