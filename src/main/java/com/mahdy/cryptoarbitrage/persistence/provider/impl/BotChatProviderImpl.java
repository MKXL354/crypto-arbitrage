package com.mahdy.cryptoarbitrage.persistence.provider.impl;

import com.mahdy.cryptoarbitrage.core.model.BotChat;
import com.mahdy.cryptoarbitrage.persistence.assembler.BotChatAssembler;
import com.mahdy.cryptoarbitrage.persistence.provider.BotChatProvider;
import com.mahdy.cryptoarbitrage.persistence.provider.entity.BotChatEntity;
import com.mahdy.cryptoarbitrage.persistence.repository.BotChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Component
@RequiredArgsConstructor
public class BotChatProviderImpl implements BotChatProvider {

    private final BotChatRepository botChatRepository;
    private final BotChatAssembler botChatAssembler;

    @Override
    public BotChat findByChatId(long chatId) {
        BotChatEntity entity = botChatRepository.findByChatIdIs(chatId);
        return botChatAssembler.toBotChat(entity);
    }

    @Override
    public BotChat save(BotChat botChat) {
        BotChatEntity entity = botChatAssembler.toBotChatEntity(botChat);
        entity = botChatRepository.save(entity);
        botChat.setId(entity.getId());
        return botChat;
    }

    @Override
    public List<BotChat> getAll() {
        List<BotChatEntity> allBotChatEntities = botChatRepository.findAllBySubbedIs(true);
        return botChatAssembler.toBotChatList(allBotChatEntities);
    }
}
