package com.mahdy.cryptoarbitrage.invoker.provider.impl;

import com.mahdy.cryptoarbitrage.core.model.BotChat;
import com.mahdy.cryptoarbitrage.invoker.dto.request.BotSendMessageRequest;
import com.mahdy.cryptoarbitrage.invoker.feignclient.BotFeignClient;
import com.mahdy.cryptoarbitrage.invoker.provider.BotProvider;
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
public class BotProviderImpl implements BotProvider {

    private final BotFeignClient botFeignClient;

    @Override
    public void sendMessage(BotChat botChat, String text) {
        long chatId = botChat.getChatId();
        BotSendMessageRequest request = new BotSendMessageRequest();
        request.setChat_id(chatId);
        request.setText(text);
        log.info("sending message: {} to chat id: {}", text, chatId);
        botFeignClient.sendMessage(request);
    }
}
