package com.mahdy.cryptoarbitrage.invoker.feignclient;

import com.mahdy.cryptoarbitrage.invoker.dto.response.WallexCoinPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@FeignClient(name = "wallex", url = "${external.wallex.base-api-url}")
public interface WallexFeignClient {

    String V1_COIN_PRICE_LIST = "/v1/coin-price-list";

    @GetMapping(V1_COIN_PRICE_LIST)
    WallexCoinPriceResponse getCoinPrice(@RequestParam String keys);
}
