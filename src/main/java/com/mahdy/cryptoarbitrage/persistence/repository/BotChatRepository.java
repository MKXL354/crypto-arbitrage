package com.mahdy.cryptoarbitrage.persistence.repository;

import com.mahdy.cryptoarbitrage.persistence.provider.entity.BotChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mehdi Kamali
 * @since 14/10/2025
 */
@Repository
public interface BotChatRepository extends JpaRepository<BotChatEntity, Long> {

    BotChatEntity findByChatIdIs(long chatId);

    List<BotChatEntity> findAllBySubbedIs(boolean isSubbed);
}
