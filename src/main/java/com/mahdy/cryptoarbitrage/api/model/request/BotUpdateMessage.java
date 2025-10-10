package com.mahdy.cryptoarbitrage.api.model.request;

import lombok.Data;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class BotUpdateMessage {

    private long message_id;
    private BotUser from;
    private long date;
    private BotChat chat;
    private String text;
}
