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
public class RegistrationService {

    private final BotProvider botProvider;

//    TODO: replace with actual db later
    private final ChatIdSet chatIdSet;

//    TODO: move this to db?
private final String registrationToken;

    public RegistrationService(BotProvider botProvider, ChatIdSet chatIdSet,
                               @Value("${service.arbitrage.registration-token}") String registrationToken) {
        if (!StringUtils.hasText(registrationToken)) {
            throw new IllegalStateException("Missing required config: service.arbitrage.registration-token");
        }
        this.botProvider = botProvider;
        this.chatIdSet = chatIdSet;
        this.registrationToken = registrationToken;
    }

    public void registerUser(long chatId, String receivedRegistrationToken) {
        if (registrationToken.equals(receivedRegistrationToken)) {
            chatIdSet.getChatIds().add(chatId);
            botProvider.sendMessage(chatId, "registration successful");
        }
    }

    public void unregisterUser(long chatId) {
        chatIdSet.getChatIds().remove(chatId);
        botProvider.sendMessage(chatId, "unregistration successful");
    }
}
