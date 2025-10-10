package com.mahdy.cryptoarbitrage.invoker.provider;

import com.mahdy.cryptoarbitrage.invoker.dto.response.WallexCoinPriceResponse;
import com.mahdy.cryptoarbitrage.invoker.feignclient.WallexFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Component
@RequiredArgsConstructor
public class WallexProvider {

    private final WallexFeignClient wallexFeignClien;

    public WallexCoinPriceResponse getWallexCoinPriceResponse(String keys) {
        return wallexFeignClien.getCoinPrice(keys);
    }
}
