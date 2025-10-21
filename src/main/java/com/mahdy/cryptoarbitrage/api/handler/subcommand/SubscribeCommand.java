package com.mahdy.cryptoarbitrage.api.handler.subcommand;

import com.mahdy.cryptoarbitrage.core.model.enumeration.Currency;
import com.mahdy.cryptoarbitrage.util.command.BaseCommand;
import lombok.Data;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
@Command(name = "sub", description = "Subscribe to crypto arbitrage service")
@Component
@Data
public class SubscribeCommand extends BaseCommand {

    @Option(names = "--token", required = true)
    String token;

    @Option(names = "--currency", required = true)
    Currency currency;
}
