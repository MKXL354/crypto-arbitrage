package com.mahdy.cryptoarbitrage.persistence.provider.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Entity
@Table(name = "BOT_CHAT")
@Getter
@Setter
public class BotChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT")
    private long id;

    @Column(name = "USER_ID", columnDefinition = "BIGINT", nullable = false)
    private long userId;

    @Column(name = "USER_FIRST_NAME", columnDefinition = "VARCHAR(100)")
    private String userFirstName;

    @Column(name = "CHAT_ID", columnDefinition = "BIGINT", nullable = false)
    private long chatId;

    @Column(name = "SUBBED", columnDefinition = "BOOLEAN", nullable = false)
    private boolean subbed;

    @Column(name = "LAST_SUB_TIME", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime lastSubTime;

    @Column(name = "LAST_UNSUB_TIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastUnsubTime;
}
