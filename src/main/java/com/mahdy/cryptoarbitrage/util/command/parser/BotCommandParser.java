package com.mahdy.cryptoarbitrage.util.command.parser;

import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import com.mahdy.cryptoarbitrage.util.command.executor.CommandExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
@Component
@RequiredArgsConstructor
public class BotCommandParser implements CommandParser<BotUpdateRequest> {

    private final CommandExecutor commandExecutor;

    public void parse(BotUpdateRequest botUpdateRequest) {
        String command = botUpdateRequest.getMessage().getText();
        String[] commandParts = command.trim().split("\\s+");
        commandExecutor.executeCommand(commandParts);
    }
}
