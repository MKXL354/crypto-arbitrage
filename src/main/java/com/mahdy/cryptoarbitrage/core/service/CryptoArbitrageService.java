package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.core.model.ArbitrageOpportunity;
import com.mahdy.cryptoarbitrage.core.model.BotChat;
import com.mahdy.cryptoarbitrage.core.model.enumeration.Currency;
import com.mahdy.cryptoarbitrage.invoker.provider.BotProvider;
import com.mahdy.cryptoarbitrage.invoker.provider.NobitexProvider;
import com.mahdy.cryptoarbitrage.invoker.provider.WallexProvider;
import com.mahdy.cryptoarbitrage.persistence.provider.ArbitrageOpportunityProvider;
import com.mahdy.cryptoarbitrage.persistence.provider.BotChatProvider;
import com.mahdy.cryptoarbitrage.persistence.provider.MetricsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    private final BotChatProvider botChatProvider;
    private final MetricsProvider metricsProvider;
    private final ArbitrageOpportunityProvider arbitrageOpportunityProvider;

    public void findArbitrageOpportunity() {
//        TODO: only BTC and TMN for now? others require extra impl and not just enum
        Currency srcCurrency = Currency.BTC;
        Currency dstCurrency = Currency.TMN;
        BigDecimal nobitexPrice = nobitexProvider.getNobitexMarketStats(srcCurrency, Currency.RLS);
        BigDecimal wallexPrice = wallexProvider.getWallexCoinPrice(srcCurrency);
        if (nobitexPrice.compareTo(wallexPrice) == 0) {
            return;
        }

        ArbitrageOpportunity arbitrageOpportunity = generateArbitrageOpportunity(srcCurrency, dstCurrency, nobitexPrice, wallexPrice);
        log.info("arbitrageOpportunity: {}", arbitrageOpportunity);
        metricsProvider.incrementArbitrageOpportunitiesCount();
        metricsProvider.updateArbitrageDifferencePercent(arbitrageOpportunity.getDifferencePercent());
//        TODO: make db and text async?
        try {
            arbitrageOpportunityProvider.save(arbitrageOpportunity);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }

        String textMessage = generateTextMessage(arbitrageOpportunity);
        log.info("textMessage: {}", textMessage);
        List<BotChat> allBotChatList = botChatProvider.getAll();
        for (BotChat botChat : allBotChatList) {
            try {
                botProvider.sendMessage(botChat.getChatId(), textMessage);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }
    }

    private ArbitrageOpportunity generateArbitrageOpportunity(Currency srcCurrency, Currency dstCurrency, BigDecimal nobitexPrice, BigDecimal wallexPrice) {
        ArbitrageOpportunity arbitrageOpportunity = new ArbitrageOpportunity();
        arbitrageOpportunity.setSrcCurrency(srcCurrency);
        arbitrageOpportunity.setDstCurrency(dstCurrency);
        arbitrageOpportunity.setFindTime(LocalDateTime.now());
        arbitrageOpportunity.setNobitexPrice(nobitexPrice);
        arbitrageOpportunity.setWallexPrice(wallexPrice);

        BigDecimal difference;
        BigDecimal differencePercent;
        if (nobitexPrice.compareTo(wallexPrice) > 0) {
            difference = nobitexPrice.subtract(wallexPrice);
            differencePercent = calculateDifferencePercent(difference, wallexPrice);
        } else {
            difference = wallexPrice.subtract(nobitexPrice);
            differencePercent = calculateDifferencePercent(difference, nobitexPrice);
        }

        arbitrageOpportunity.setDifference(difference);
        arbitrageOpportunity.setDifferencePercent(differencePercent);
        return arbitrageOpportunity;
    }

    private BigDecimal calculateDifferencePercent(BigDecimal difference, BigDecimal smaller) {
        return difference.divide(smaller, MathContext.DECIMAL64)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private String generateTextMessage(ArbitrageOpportunity arbitrageOpportunity) {
        String formattedTime = arbitrageOpportunity.getFindTime().format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm:ss"));
        String formattedNobitexPrice = NumberFormat.getInstance().format(arbitrageOpportunity.getNobitexPrice());
        String formattedWallexPrice = NumberFormat.getInstance().format(arbitrageOpportunity.getWallexPrice());
        return """
                Comparing currency price: %s to %s
                Time: %s
                Nobitex price: %s Tomans
                Wallex price: %s Tomans
                """.formatted(arbitrageOpportunity.getSrcCurrency().name(), arbitrageOpportunity.getDstCurrency().name(),
                formattedTime, formattedNobitexPrice, formattedWallexPrice)
                + generateDifferenceText(arbitrageOpportunity);
    }

    private String generateDifferenceText(ArbitrageOpportunity arbitrageOpportunity) {
        String biggerName;
        String smallerName;
        if (arbitrageOpportunity.getNobitexPrice().compareTo(arbitrageOpportunity.getWallexPrice()) > 0) {
            biggerName = "Nobitex";
            smallerName = "Wallex";
        } else {
            biggerName = "Wallex";
            smallerName = "Nobitex";
        }
        String formattedDifference = NumberFormat.getInstance().format(arbitrageOpportunity.getDifference());
        return """
                %s is more than %s by %s Tomans and %s percent
                """.formatted(biggerName, smallerName, formattedDifference, arbitrageOpportunity.getDifferencePercent());
    }
}
