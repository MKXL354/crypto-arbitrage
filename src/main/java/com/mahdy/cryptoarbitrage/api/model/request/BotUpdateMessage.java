package com.mahdy.cryptoarbitrage.api.model.request;

import lombok.Data;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class BotUpdateMessage {

    private long message_id;
    private BotUpdateUser from;
    private long date;
    private BotUpdateChat chat;
    private String text;
}
