package com.mahdy.cryptoarbitrage.invoker.provider;

import com.mahdy.cryptoarbitrage.invoker.dto.request.BotSendMessageRequest;
import com.mahdy.cryptoarbitrage.invoker.feignclient.BotFeignClient;
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
public class BotProvider {

    private final BotFeignClient botFeignClient;

    public void sendMessage(long chatId, String text) {
        BotSendMessageRequest request = new BotSendMessageRequest();
        request.setChat_id(chatId);
        request.setText(text);
        log.info("sending message: {} to chat id: {}", text, chatId);
        botFeignClient.sendMessage(request);
    }
}
