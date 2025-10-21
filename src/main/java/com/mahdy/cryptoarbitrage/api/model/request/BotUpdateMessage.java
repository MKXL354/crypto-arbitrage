package com.mahdy.cryptoarbitrage.api.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class BotUpdateMessage {

    private long message_id;
    @Valid
    private BotUpdateUser from;
    private long date;
    @Valid
    private BotUpdateChat chat;
    @NotBlank
    private String text;
}
