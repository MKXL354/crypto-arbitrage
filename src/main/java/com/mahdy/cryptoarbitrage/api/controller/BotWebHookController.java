package com.mahdy.cryptoarbitrage.api.controller;

import com.mahdy.cryptoarbitrage.api.facade.BotWebHookFacade;
import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import com.mahdy.cryptoarbitrage.core.service.UserService;
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
public class BotWebHookController implements BotWebHookFacade {

    private final UserService userService;

    @Override
    public void registerUpdate(BotUpdateRequest botUpdateRequest) {
//        TODO: service and strategy to handle different messages in the future
        userService.registerUser(botUpdateRequest);
    }
}
