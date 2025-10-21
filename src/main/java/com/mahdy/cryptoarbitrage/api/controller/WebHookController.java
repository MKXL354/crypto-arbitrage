package com.mahdy.cryptoarbitrage.api.controller;

import com.mahdy.cryptoarbitrage.api.facade.WebHookFacade;
import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import com.mahdy.cryptoarbitrage.util.command.parser.CommandParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class WebHookController implements WebHookFacade {

    private final CommandParser<BotUpdateRequest> commandParser;

    @Override
    public void registerUpdate(BotUpdateRequest botUpdateRequest) {
        commandParser.parse(botUpdateRequest);
    }
}
