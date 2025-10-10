package com.mahdy.cryptoarbitrage.core.model.enumeration;

import lombok.Getter;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Getter
public enum Coin {

    BTC("btc"),
    RLS("rls"),
    TMN("tmn");

    private final String lowerCaseName;

    Coin(String lowerCaseName) {
        this.lowerCaseName = lowerCaseName;
    }
}
