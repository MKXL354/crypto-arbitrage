package com.mahdy.cryptoarbitrage.api.controller;

import com.mahdy.cryptoarbitrage.api.facade.WebHookFacade;
import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import com.mahdy.cryptoarbitrage.core.handler.UpdateRequestHandler;
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

    private final UpdateRequestHandler updateRequestHandler;

    @Override
    public void registerUpdate(BotUpdateRequest botUpdateRequest) {
        updateRequestHandler.handleUpdate(botUpdateRequest);
    }
}
