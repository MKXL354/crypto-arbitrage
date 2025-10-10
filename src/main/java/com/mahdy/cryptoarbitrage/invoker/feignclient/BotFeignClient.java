package com.mahdy.cryptoarbitrage.invoker.feignclient;

import com.mahdy.cryptoarbitrage.invoker.dto.request.BotSendMessageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@FeignClient(name = "bot", url = "${external.bot.base-api-url}")
public interface BotFeignClient {

    String SEND_MESSAGE = "/sendMessage";

    @PostMapping(path = SEND_MESSAGE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void sendMessage(@RequestBody BotSendMessageRequest message);
}
