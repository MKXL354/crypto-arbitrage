package com.mahdy.cryptoarbitrage.api.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class BotUpdateMessage {

    private long message_id;
    @Valid
    @NotNull
    private BotUpdateUser from;
    private long date;
    @Valid
    @NotNull
    private BotUpdateChat chat;
    @NotBlank
    private String text;
}
