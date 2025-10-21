package com.mahdy.cryptoarbitrage.util.command.parser;

import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import com.mahdy.cryptoarbitrage.util.command.executor.CommandExecutor;
import com.mahdy.cryptoarbitrage.util.command.model.CommandContext;
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
    private final CommandContext commandContext;

    public void parse(BotUpdateRequest botUpdateRequest) {
        String command = botUpdateRequest.getMessage().getText();
        String[] commandParts = command.trim().split("\\s+");
        fillCommandContext(botUpdateRequest);
        commandExecutor.executeCommand(commandParts);
    }

    private void fillCommandContext(BotUpdateRequest botUpdateRequest) {
        commandContext.setUserId(botUpdateRequest.getMessage().getFrom().getId());
        commandContext.setUserFirstName(botUpdateRequest.getMessage().getFrom().getFirst_name());
        commandContext.setChatId(botUpdateRequest.getMessage().getChat().getId());
    }
}
