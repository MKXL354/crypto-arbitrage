package com.mahdy.cryptoarbitrage.util.command.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
@Component
@RequestScope
@Data
public class CommandContext {

    private long userId;
    private String userFirstName;
    private long chatId;
}
