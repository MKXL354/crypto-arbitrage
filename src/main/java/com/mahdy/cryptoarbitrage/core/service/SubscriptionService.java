package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.invoker.provider.BotProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final BotProvider botProvider;

    private final ChatIdProvider chatIdProvider;

    //    TODO: move this to db? and have actual updating and setting business on it
    @Value("${service.arbitrage.subscription-token}")
    private String subscriptionToken;

    @PostConstruct
    public void init() {
        if (!StringUtils.hasText(subscriptionToken)) {
            throw new IllegalStateException("Missing required config: service.arbitrage.subscription-token");
        }
    }

    public void subscribe(long chatId, String receivedRegistrationToken) {
        if (subscriptionToken.equals(receivedRegistrationToken)) {
            chatIdProvider.getChatIds().add(chatId);
            botProvider.sendMessage(chatId, "subscription successful");
        }
    }

    public void unsubscribe(long chatId) {
        chatIdProvider.getChatIds().remove(chatId);
        botProvider.sendMessage(chatId, "unsubscription successful");
    }
}
