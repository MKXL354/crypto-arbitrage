package com.mahdy.cryptoarbitrage.persistence.provider;

import com.mahdy.cryptoarbitrage.core.model.BotChat;

import java.util.List;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
public interface BotChatProvider {

    BotChat findByChatId(long chatId);

    BotChat save(BotChat botChat);

    List<BotChat> getAll();
}
