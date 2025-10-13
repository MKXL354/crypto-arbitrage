package com.mahdy.cryptoarbitrage.core.service;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mehdi Kamali
 * @since 10/10/2025
 */
@Component
@Getter
public class ChatIdProvider {

    private final Set<Long> chatIds = ConcurrentHashMap.newKeySet();
}
//TODO: replace with actual db later
