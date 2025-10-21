package com.mahdy.cryptoarbitrage.api.handler;

import com.mahdy.cryptoarbitrage.api.handler.subcommand.SubscribeCommand;
import com.mahdy.cryptoarbitrage.api.handler.subcommand.UnsubscribeCommand;
import com.mahdy.cryptoarbitrage.util.command.model.annotation.CommandMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
@Component
@Slf4j
public class SubscriptionHandler {

    @CommandMethod("sub")
    public void sub(SubscribeCommand subCommand) {
//        TODO: request context and fill userId, userFirstName, chatId
        log.info("sub command received");
        log.info("subCommand: {}", subCommand);
    }

    @CommandMethod("unsub")
    public void unsub(UnsubscribeCommand unsubscribeCommand) {
        log.info("unsub command received");
        log.info("unsubCommand: {}", unsubscribeCommand);
    }
}
