package com.mahdy.cryptoarbitrage.invoker.provider;

import com.mahdy.cryptoarbitrage.invoker.dto.response.NobitexMarketStatsResponse;
import com.mahdy.cryptoarbitrage.invoker.feignclient.NobitexFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Component
@RequiredArgsConstructor
public class NobitexProvider {

    private final NobitexFeignClient nobitexFeignClient;

//    TODO: enum as input? definitely service domain usage
    public NobitexMarketStatsResponse getNobitexMarketStats(String src, String dst) {
        return nobitexFeignClient.getMarketStats(src, dst);
    }
}
