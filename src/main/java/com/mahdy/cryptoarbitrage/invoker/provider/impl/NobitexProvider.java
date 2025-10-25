package com.mahdy.cryptoarbitrage.invoker.provider.impl;

import com.mahdy.cryptoarbitrage.core.model.annotation.TrackExternalApiMetrics;
import com.mahdy.cryptoarbitrage.core.model.enumeration.Currency;
import com.mahdy.cryptoarbitrage.core.model.enumeration.ExternalApi;
import com.mahdy.cryptoarbitrage.invoker.dto.response.NobitexMarketStatsResponse;
import com.mahdy.cryptoarbitrage.invoker.feignclient.NobitexFeignClient;
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
public class NobitexProvider implements ExchangeProvider {

    private final NobitexFeignClient nobitexFeignClient;

    @Override
    @TrackExternalApiMetrics(ExternalApi.NOBITEX)
    public BigDecimal getCurrencyPrice(Currency source, Currency destination) {
//        TODO: clean this up with assemblers? too much effort, but this is really unreliable
        if (destination.equals(Currency.TMN)) {
            destination = Currency.RLS;
        }
        String srcCurrency = source.getLowerCaseName();
        String destCurrency = destination.getLowerCaseName();
        NobitexMarketStatsResponse response = nobitexFeignClient.getMarketStats(srcCurrency, destCurrency);
        BigDecimal rlsPrice = response.getStats().get(srcCurrency + "-" + destCurrency).getLatest();
        return rlsPrice.divide(BigDecimal.TEN, RoundingMode.DOWN);
    }
}
