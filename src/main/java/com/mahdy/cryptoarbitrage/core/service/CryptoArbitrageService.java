package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Coin;
import com.mahdy.cryptoarbitrage.invoker.provider.NobitexProvider;
import com.mahdy.cryptoarbitrage.invoker.provider.WallexProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Service
@RequiredArgsConstructor
public class CryptoArbitrageService {

    private final NobitexProvider nobitexProvider;
    private final WallexProvider wallexProvider;

    public void findArbitrageOpportunity() {
        System.out.println(nobitexProvider.getNobitexMarketStats(Coin.BTC, Coin.RLS));
        System.out.println(wallexProvider.getWallexCoinPriceResponse(Coin.BTC));
    }
}
