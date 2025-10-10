package com.mahdy.cryptoarbitrage.invoker.dto.request;

import lombok.Data;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class BotSendMessageRequest {

    private long chat_id;
    private String text;
}
