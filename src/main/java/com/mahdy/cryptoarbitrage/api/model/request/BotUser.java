package com.mahdy.cryptoarbitrage.api.model.request;

import lombok.Data;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Data
public class BotUser {

    private long id;
    private boolean is_bot;
    private String first_name;
}
