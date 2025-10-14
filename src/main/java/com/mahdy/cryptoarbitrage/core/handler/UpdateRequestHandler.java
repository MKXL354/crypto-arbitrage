package com.mahdy.cryptoarbitrage.core.handler;

import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateMessage;
import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import com.mahdy.cryptoarbitrage.core.model.BotChat;
import com.mahdy.cryptoarbitrage.core.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateRequestHandler {

    private final SubscriptionService subscriptionService;

    public void handleUpdate(BotUpdateRequest botUpdateRequest) {
        log.info("received update request: {}", botUpdateRequest);
        long chatId = botUpdateRequest.getMessage().getChat().getId();
        String command = botUpdateRequest.getMessage().getText();
        String[] commandParts = command.trim().split("\\s+");
        if (commandParts.length < 1) {
//            TODO: error handling? maybe throw exception and send error message in global handler
            return;
        }
        if ("sub".equals(commandParts[0])) {
            log.info("invoked subscribe()");
            handleSubscribe(botUpdateRequest, commandParts);
        } else if ("unsub".equals(commandParts[0])) {
            log.info("invoked unsubscribe()");
            handleUnsubscribe(chatId);
        }
    }

    private void handleSubscribe(BotUpdateRequest botUpdateRequest, String[] commandParts) {
        if (commandParts.length != 2) {
            return;
        }
        String receivedSubscriptionToken = commandParts[1];
        BotChat botChat = generateBotChat(botUpdateRequest);
        subscriptionService.subscribe(botChat, receivedSubscriptionToken);
    }

    private void handleUnsubscribe(long chatId) {
        subscriptionService.unsubscribe(chatId);
    }

    private BotChat generateBotChat(BotUpdateRequest botUpdateRequest) {
        BotChat botChat = new BotChat();
        BotUpdateMessage botUpdateMessage = botUpdateRequest.getMessage();
        botChat.setUserId(botUpdateMessage.getFrom().getId());
        botChat.setUserFirstName(botUpdateMessage.getFrom().getFirst_name());
        botChat.setChatId(botUpdateMessage.getChat().getId());
        return botChat;
    }
}
