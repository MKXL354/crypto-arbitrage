package com.mahdy.cryptoarbitrage.invoker.provider;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Coin;
import com.mahdy.cryptoarbitrage.invoker.dto.response.NobitexMarketStatsResponse;
import com.mahdy.cryptoarbitrage.invoker.feignclient.NobitexFeignClient;
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
public class NobitexProvider {

    private final NobitexFeignClient nobitexFeignClient;

    public BigDecimal getNobitexMarketStats(Coin source, Coin destination) {
        String srcCurrency = source.getLowerCaseName();
        String destCurrency = destination.getLowerCaseName();
        NobitexMarketStatsResponse response = nobitexFeignClient.getMarketStats(srcCurrency, destCurrency);
        BigDecimal rlsPrice = response.getStats().get(srcCurrency + "-" + destCurrency).getLatest();
        return rlsPrice.divide(BigDecimal.TEN, RoundingMode.DOWN);
    }
}
