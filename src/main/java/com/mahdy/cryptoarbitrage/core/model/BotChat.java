package com.mahdy.cryptoarbitrage.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Data
public class BotChat {

    private long id;
    private long userId;
    private String userFirstName;
    private long chatId;
    private boolean subbed;
    private LocalDateTime lastSubTime;
    private LocalDateTime lastUnsubTime;
}
