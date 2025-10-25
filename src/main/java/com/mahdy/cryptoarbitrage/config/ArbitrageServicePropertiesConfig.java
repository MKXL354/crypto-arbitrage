package com.mahdy.cryptoarbitrage.config;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Currency;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mehdi Kamali
 * @since 25/10/2025
 */
@Component
@ConfigurationProperties(prefix = "service.arbitrage")
@Data
public class ArbitrageServicePropertiesConfig {

    private List<Currency> supportedCurrencies;
}
