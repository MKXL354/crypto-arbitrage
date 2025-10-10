package com.mahdy.cryptoarbitrage.core.model.enumeration;

import lombok.Getter;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Getter
public enum Currency {

    BTC("btc"),
    RLS("rls"),
    TMN("tmn");

    private final String lowerCaseName;

    Currency(String lowerCaseName) {
        this.lowerCaseName = lowerCaseName;
    }
}
