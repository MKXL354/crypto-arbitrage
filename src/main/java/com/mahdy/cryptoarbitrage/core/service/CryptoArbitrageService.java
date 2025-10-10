package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Coin;
import com.mahdy.cryptoarbitrage.invoker.provider.BotProvider;
import com.mahdy.cryptoarbitrage.invoker.provider.NobitexProvider;
import com.mahdy.cryptoarbitrage.invoker.provider.WallexProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CryptoArbitrageService {

    private final NobitexProvider nobitexProvider;
    private final WallexProvider wallexProvider;
    private final BotProvider botProvider;
    private final ChatIdSet chatIdSet;

    public void findArbitrageOpportunity() {
        BigDecimal nobitexPrice = nobitexProvider.getNobitexMarketStats(Coin.BTC, Coin.RLS);
        BigDecimal wallexPrice = wallexProvider.getWallexCoinPriceResponse(Coin.BTC);
        String text = """
                      Nobitex Price: %s
                      Wallex Price: %s
                      """.formatted(nobitexPrice, wallexPrice);
//        TODO: make this async
        log.info(text);
        for (Long chatId : chatIdSet.getChatIds()) {
            botProvider.sendMessage(chatId, text);
        }
    }
}
