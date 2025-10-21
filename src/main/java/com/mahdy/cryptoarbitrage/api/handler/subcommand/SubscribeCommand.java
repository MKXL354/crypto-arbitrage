package com.mahdy.cryptoarbitrage.api.handler.subcommand;

import com.mahdy.cryptoarbitrage.util.command.BaseCommand;
import lombok.Data;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
@Command(name = "sub", description = "Subscribe to crypto arbitrage service")
@Component
@Data
public class SubscribeCommand extends BaseCommand {

    @Parameters(index = "0")
    String token;
}
