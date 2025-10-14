package com.mahdy.cryptoarbitrage.persistence.assembler;

import com.mahdy.cryptoarbitrage.core.model.BotChat;
import com.mahdy.cryptoarbitrage.persistence.provider.entity.BotChatEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Component
public class BotChatAssembler {

    public BotChatEntity toBotChatEntity(BotChat botChat) {
        BotChatEntity botChatEntity = new BotChatEntity();
        botChatEntity.setId(botChat.getId());
        botChatEntity.setUserId(botChat.getUserId());
        botChatEntity.setUserFirstName(botChat.getUserFirstName());
        botChatEntity.setChatId(botChat.getChatId());
        botChatEntity.setSubbed(botChat.isSubbed());
        botChatEntity.setLastSubTime(botChat.getLastSubTime());
        botChatEntity.setLastUnsubTime(botChat.getLastUnsubTime());
        return botChatEntity;
    }

    public BotChat toBotChat(BotChatEntity botChatEntity) {
        if (botChatEntity == null) {
            return null;
        }

        BotChat botChat = new BotChat();
        botChat.setId(botChatEntity.getId());
        botChat.setUserId(botChatEntity.getUserId());
        botChat.setUserFirstName(botChatEntity.getUserFirstName());
        botChat.setChatId(botChatEntity.getChatId());
        botChat.setSubbed(botChatEntity.isSubbed());
        botChat.setLastSubTime(botChatEntity.getLastSubTime());
        botChat.setLastUnsubTime(botChatEntity.getLastUnsubTime());
        return botChat;
    }

    public List<BotChat> toBotChatList(List<BotChatEntity> botChatEntityList) {
        if (CollectionUtils.isEmpty(botChatEntityList)) {
            return Collections.emptyList();
        }

        List<BotChat> botChatList = new ArrayList<>();
        for  (BotChatEntity botChatEntity : botChatEntityList) {
            BotChat botChat = toBotChat(botChatEntity);
            botChatList.add(botChat);
        }
        return botChatList;
    }
}
