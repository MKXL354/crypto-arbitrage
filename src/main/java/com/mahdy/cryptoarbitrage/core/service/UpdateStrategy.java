package com.mahdy.cryptoarbitrage.core.service;

import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateStrategy {

    private final RegistrationService registrationService;

    public void handleUpdate(BotUpdateRequest botUpdateRequest) {
        log.info("received request: {}", botUpdateRequest);
        long chatId = botUpdateRequest.getMessage().getChat().getId();
        String command = botUpdateRequest.getMessage().getText();
        command = command.replaceAll("\\s+", "");
        String[] commandParts = command.split(":");
        if (commandParts.length < 1) {
            return;
        }
        if ("register".equals(commandParts[0])) {
            log.info("called registerUser()");
            handleRegister(chatId, commandParts);
        }
        else if ("unregister".equals(commandParts[0])) {
            log.info("called unregisterUser()");
            handleUnregister(chatId);
        }
    }

    private void handleRegister(long chatId, String[] commandParts) {
        if (commandParts.length != 2) {
            return;
        }
        String receivedRegistrationToken = commandParts[1];
        registrationService.registerUser(chatId, receivedRegistrationToken);
    }

    private void handleUnregister(long chatId) {
        registrationService.unregisterUser(chatId);
    }
}
//TODO: interfacing and auto handling? requires uniform service api
//TODO: auto logging of invoked services not manual like here
//TODO: error handling? maybe throw exception and send error message in global handler
