package com.mahdy.cryptoarbitrage.api.handler.subcommand;

import com.mahdy.cryptoarbitrage.util.command.BaseCommand;
import lombok.Data;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
@CommandLine.Command(name = "unsub", description = "Unsubscribe from crypto arbitrage service")
@Component
@Data
public class UnsubscribeCommand extends BaseCommand {
}
