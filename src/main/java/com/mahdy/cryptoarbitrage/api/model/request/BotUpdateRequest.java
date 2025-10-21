package com.mahdy.cryptoarbitrage.api.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class BotUpdateRequest {

    private long update_id;
    @Valid
    @NotNull
    private BotUpdateMessage message;
}
