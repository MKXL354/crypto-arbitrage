package com.mahdy.cryptoarbitrage.util.command.parser;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
public interface CommandParser<T> {

    void parse(T command);
}
