package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.invoker.provider.BotProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Service
public class SubscriptionService {

    private final BotProvider botProvider;

//    TODO: replace with actual db later
private final ChatIdProvider chatIdProvider;

    //    TODO: move this to db? and have actual updating and setting business on it
private final String subscriptionToken;

    public SubscriptionService(BotProvider botProvider, ChatIdProvider chatIdProvider,
                               @Value("${service.arbitrage.subscription-token}") String subscriptionToken) {
        if (!StringUtils.hasText(subscriptionToken)) {
            throw new IllegalStateException("Missing required config: service.arbitrage.subscription-token");
        }
        this.botProvider = botProvider;
        this.chatIdProvider = chatIdProvider;
        this.subscriptionToken = subscriptionToken;
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
