package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Currency;
import com.mahdy.cryptoarbitrage.invoker.provider.BotProvider;
import com.mahdy.cryptoarbitrage.invoker.provider.NobitexProvider;
import com.mahdy.cryptoarbitrage.invoker.provider.WallexProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private final ChatIdProvider chatIdProvider;

    public void findArbitrageOpportunity() {
//        TODO: only BTC for now? others require extra impl and not just enum
        BigDecimal nobitexPrice = nobitexProvider.getNobitexMarketStats(Currency.BTC, Currency.RLS);
        BigDecimal wallexPrice = wallexProvider.getWallexCoinPriceResponse(Currency.BTC);

        String text = generateTextMessage(Currency.BTC, nobitexPrice, wallexPrice);
//        TODO: make this async
        log.info(text);
        for (Long chatId : chatIdProvider.getChatIds()) {
            botProvider.sendMessage(chatId, text);
        }
    }

    private String generateTextMessage(Currency currency, BigDecimal nobitexPrice, BigDecimal wallexPrice) {
        LocalDateTime now = LocalDateTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm:ss"));
        String formattedNobitexPrice = NumberFormat.getInstance().format(nobitexPrice);
        String formattedWallexPrice = NumberFormat.getInstance().format(wallexPrice);
        return """
                Comparing currency price: %s
                Time: %s
                Nobitex price: %s Tomans
                Wallex price: %s Tomans
                """.formatted(currency.name(), formattedTime, formattedNobitexPrice, formattedWallexPrice)
                + generateDifferenceText(nobitexPrice, wallexPrice);
    }

    String generateDifferenceText(BigDecimal nobitexPrice, BigDecimal wallexPrice) {
        String differenceText = "";
        if (nobitexPrice.compareTo(wallexPrice) != 0) {
            String biggerName;
            String smallerName;
            BigDecimal difference;
            BigDecimal differencePercent;
            if (nobitexPrice.compareTo(wallexPrice) > 0) {
                biggerName = "Nobitex";
                smallerName = "Wallex";
                difference = nobitexPrice.subtract(wallexPrice);
                differencePercent = calculateDifferencePercent(difference, wallexPrice);
            } else {
                biggerName = "Wallex";
                smallerName = "Nobitex";
                difference = wallexPrice.subtract(nobitexPrice);
                differencePercent = calculateDifferencePercent(difference, nobitexPrice);
            }
            String formattedDifference = NumberFormat.getInstance().format(difference);
            differenceText = """
                    %s is more than %s by %s Tomans and %s percent
                    """.formatted(biggerName, smallerName, formattedDifference, differencePercent);
        }
        return differenceText;
    }

    private BigDecimal calculateDifferencePercent(BigDecimal difference, BigDecimal smaller) {
        return difference.divide(smaller, MathContext.DECIMAL64)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
