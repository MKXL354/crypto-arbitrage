package com.mahdy.cryptoarbitrage.invoker.provider;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Currency;

import java.math.BigDecimal;

/**
 * @author Mehdi Kamali
 * @since 25/10/2025
 */
public interface ExchangeProvider {

    BigDecimal getCurrencyPrice(Currency source, Currency destination);
}
