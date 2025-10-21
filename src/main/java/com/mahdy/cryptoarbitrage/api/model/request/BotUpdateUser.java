package com.mahdy.cryptoarbitrage.api.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class BotUpdateUser {

    @NotNull
    private long id;
    private boolean is_bot;
    @NotBlank
    private String first_name;
}
