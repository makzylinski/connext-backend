package com.test.springboot.demo.mycoolapp.repository;

import com.test.springboot.demo.mycoolapp.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query("SELECT m FROM MessageEntity m WHERE " +
            "(m.sender_id = :user1 AND m.recipient_id = :user2) OR " +
            "(m.sender_id = :user2 AND m.recipient_id = :user1) " +
            "ORDER BY m.timestamp ASC")

    List<MessageEntity> findConversationsBetweenUsers(@Param("user1") Long user1, @Param("user2") Long user2);
}
