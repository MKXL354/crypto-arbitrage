package com.mahdy.cryptoarbitrage.core.model.enumeration;

import lombok.Getter;

/**
 * @author Mehdi Kamali
 * @since 13/10/2025
 */
@Getter
public enum ExternalApi {

    NOBITEX("nobitex"),
    WALLEX("wallex");

    private final String name;

    private ExternalApi(String name) {
        this.name = name;
    }
}
