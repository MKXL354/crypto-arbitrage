package com.mahdy.cryptoarbitrage.invoker.provider;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Coin;
import com.mahdy.cryptoarbitrage.invoker.dto.response.WallexCoinPriceResponse;
import com.mahdy.cryptoarbitrage.invoker.feignclient.WallexFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Component
@RequiredArgsConstructor
public class WallexProvider {

    private final WallexFeignClient wallexFeignClien;

    public BigDecimal getWallexCoinPriceResponse(Coin key) {
        WallexCoinPriceResponse response = wallexFeignClien.getCoinPrice(key.name());
        BigDecimal tmnPrice = response.getResult().getMarkets().getFirst().getQuotes().get(Coin.TMN.name()).getPrice();
        return tmnPrice.setScale(0, RoundingMode.DOWN);
    }
}
