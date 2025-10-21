package com.mahdy.cryptoarbitrage.util.command.registry;

import com.mahdy.cryptoarbitrage.util.command.model.HandlerMethod;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
public interface HandlerRegistry {

    HandlerMethod getHandlerMethod(String commandName);
}
