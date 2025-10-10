package com.mahdy.cryptoarbitrage.api.controller;

import com.mahdy.cryptoarbitrage.api.facade.BotWebHookFacade;
import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@RestController
@RequestMapping
public class BotWebHookController implements BotWebHookFacade {

    @Override
    public void registerUpdate(BotUpdateRequest botUpdateRequest) {
//        TODO: service and strategy to handle different messages
//        TODO: send confirm register message
        System.out.println(botUpdateRequest);
    }
}
