package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.core.model.BotChat;
import com.mahdy.cryptoarbitrage.invoker.provider.BotProvider;
import com.mahdy.cryptoarbitrage.persistence.provider.BotChatProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final BotChatProvider botChatProvider;
    private final BotProvider botProvider;

    //    TODO: move this to db? and have actual updating and setting business on it
    @Value("${service.arbitrage.subscription-token}")
    private String subscriptionToken;

    @PostConstruct
    public void init() {
        if (!StringUtils.hasText(subscriptionToken)) {
            throw new IllegalStateException("Missing required config: service.arbitrage.subscription-token");
        }
    }

    public void subscribe(BotChat botChat, String receivedRegistrationToken) {
        if (subscriptionToken.equals(receivedRegistrationToken)) {
            BotChat savedBotChat = botChatProvider.findByChatId(botChat.getChatId());
            if (savedBotChat != null) {
                botChat = savedBotChat;
            }
            botChat.setSubbed(true);
            botChat.setLastSubTime(LocalDateTime.now());
            botChatProvider.save(botChat);
            botProvider.sendMessage(botChat, "subscription successful");
        }
    }

    public void unsubscribe(BotChat botChat) {
        long chatId = botChat.getChatId();
        BotChat savedBotChat = botChatProvider.findByChatId(chatId);
        if (savedBotChat == null) {
            return;
        }
        savedBotChat.setSubbed(false);
        savedBotChat.setLastUnsubTime(LocalDateTime.now());
        botChatProvider.save(savedBotChat);
        botProvider.sendMessage(botChat, "unsubscription successful");
    }
}
