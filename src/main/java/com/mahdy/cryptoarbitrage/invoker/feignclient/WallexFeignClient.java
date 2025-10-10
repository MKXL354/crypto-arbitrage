package com.mahdy.cryptoarbitrage.invoker.feignclient;

import com.mahdy.cryptoarbitrage.invoker.dto.response.WallexCoinPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@FeignClient(name = "wallex", url = "https://api.wallex.ir")
public interface WallexFeignClient {

    @GetMapping("/v1/coin-price-list")
    WallexCoinPriceResponse getCoinPrice(@RequestParam String keys);
}
