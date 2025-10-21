package com.mahdy.cryptoarbitrage.api.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class BotUpdateChat {

    @NotNull
    private long id;
    private String type;
}
