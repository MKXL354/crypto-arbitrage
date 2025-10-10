package com.mahdy.cryptoarbitrage.invoker.provider;

import com.mahdy.cryptoarbitrage.invoker.dto.request.BotSendMessageRequest;
import com.mahdy.cryptoarbitrage.invoker.feignclient.BotFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Component
@RequiredArgsConstructor
public class BotProvider {

    private final BotFeignClient botFeignClient;

    public void sendMessage(long chatId, String text) {
        BotSendMessageRequest request = new BotSendMessageRequest();
        request.setChat_id(chatId);
        request.setText(text);
        botFeignClient.sendMessage(request);
    }
}
