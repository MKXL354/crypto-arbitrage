package com.mahdy.cryptoarbitrage.api.facade;

import com.mahdy.cryptoarbitrage.api.model.request.BotUpdateRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
public interface WebHookFacade {

    String BASE_PATH = "api/v1/web-hook";
    String UPDATE = BASE_PATH + "/update";

    @PostMapping(path = UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void registerUpdate(@RequestBody BotUpdateRequest botUpdateRequest);
}
