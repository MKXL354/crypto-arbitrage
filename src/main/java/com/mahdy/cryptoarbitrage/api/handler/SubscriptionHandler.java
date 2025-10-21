package com.mahdy.cryptoarbitrage.api.handler;

import com.mahdy.cryptoarbitrage.api.handler.subcommand.SubscribeCommand;
import com.mahdy.cryptoarbitrage.api.handler.subcommand.UnsubscribeCommand;
import com.mahdy.cryptoarbitrage.core.model.BotChat;
import com.mahdy.cryptoarbitrage.core.service.SubscriptionService;
import com.mahdy.cryptoarbitrage.util.command.model.CommandContext;
import com.mahdy.cryptoarbitrage.util.command.model.annotation.CommandMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SubscriptionHandler {

    private final CommandContext commandContext;
    private final SubscriptionService subscriptionService;

    @CommandMethod("sub")
    public void sub(SubscribeCommand subCommand) {
        BotChat botChat = generateBotChat();
        subscriptionService.subscribe(botChat, subCommand.getToken());
    }

    @CommandMethod("unsub")
    public void unsub(UnsubscribeCommand unsubscribeCommand) {
        subscriptionService.unsubscribe(commandContext.getChatId());
    }

    private BotChat generateBotChat() {
        BotChat botChat = new BotChat();
        botChat.setUserId(commandContext.getUserId());
        botChat.setUserFirstName(commandContext.getUserFirstName());
        botChat.setChatId(commandContext.getChatId());
        return botChat;
    }
}
