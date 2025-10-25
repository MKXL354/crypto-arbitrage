package com.mahdy.cryptoarbitrage.invoker.provider.impl;

import com.mahdy.cryptoarbitrage.core.model.annotation.TrackExternalApiMetrics;
import com.mahdy.cryptoarbitrage.core.model.enumeration.Currency;
import com.mahdy.cryptoarbitrage.core.model.enumeration.ExternalApi;
import com.mahdy.cryptoarbitrage.invoker.dto.response.WallexCoinPriceResponse;
import com.mahdy.cryptoarbitrage.invoker.feignclient.WallexFeignClient;
import com.mahdy.cryptoarbitrage.invoker.provider.ExchangeProvider;
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
public class WallexProvider implements ExchangeProvider {

    private final WallexFeignClient wallexFeignClien;

    @Override
    @TrackExternalApiMetrics(ExternalApi.WALLEX)
    public BigDecimal getCurrencyPrice(Currency source, Currency destination) {
        WallexCoinPriceResponse response = wallexFeignClien.getCoinPrice(source.name());
        BigDecimal tmnPrice = response.getResult().getMarkets().getFirst().getQuotes().get(destination.name()).getPrice();
        return tmnPrice.setScale(0, RoundingMode.DOWN);
    }
}
