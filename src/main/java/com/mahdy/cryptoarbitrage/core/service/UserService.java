package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import com.mahdy.cryptoarbitrage.invoker.provider.BotProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final BotProvider botProvider;

//    TODO: replace with actual db later
    @Getter
    private final Set<Long> chatIds = ConcurrentHashMap.newKeySet();

//    TODO: change api according to controller strategy in the future
    public void registerUser(BotUpdateRequest botUpdateRequest) {
        System.out.println(botUpdateRequest);
        long chatId = botUpdateRequest.getMessage().getChat().getId();
        chatIds.add(chatId);
        botProvider.sendMessage(chatId, "received message: " + botUpdateRequest.getMessage().getText());
    }
}
