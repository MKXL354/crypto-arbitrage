package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import com.mahdy.cryptoarbitrage.invoker.provider.BotProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final BotProvider botProvider;

//    TODO: replace with actual db later
    private final ChatIdSet chatIdSet;

//    TODO: move this to db?
    @Value("${service.arbitrage.registration-token}")
    private String registrationToken;

//    TODO: strategy to handle different messages in the future? change api accordingly
    public void registerUser(BotUpdateRequest botUpdateRequest) {
        System.out.println(botUpdateRequest);
        String text = botUpdateRequest.getMessage().getText();
        text = text.replaceAll("\\s+", "");
        String[] textParts = text.split(":");
        if (textParts.length != 2) {
            return;
        }
        if ("registration-token".equals(textParts[0])) {
            if (registrationToken.equals(textParts[1])) {
                long chatId = botUpdateRequest.getMessage().getChat().getId();
                chatIdSet.getChatIds().add(chatId);
                botProvider.sendMessage(chatId, "registration successful");
            }
        }
//        TODO: error handling? maybe throw exception and send error message in global handler
    }
}
