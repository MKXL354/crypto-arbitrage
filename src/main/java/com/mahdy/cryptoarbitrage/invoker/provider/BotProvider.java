package com.mahdy.cryptoarbitrage.invoker.provider;

import com.mahdy.cryptoarbitrage.core.model.BotChat;

/**
 * @author Mehdi Kamali
 * @since 25/10/2025
 */
public interface BotProvider {

    void sendMessage(BotChat botChat, String text);
}
