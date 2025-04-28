package com.test.springboot.demo.mycoolapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sender_id;
    private Long recipient_id;
    private String content;
    private LocalDateTime timestamp;

    public MessageEntity() {}

    public MessageEntity(Long id, Long sender_id, Long recipient_id, String content, LocalDateTime timestamp) {
        this.id = id;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.content = content;
        this.timestamp = timestamp;
    }

    public MessageEntity(Long senderId, Long recipientId, String content, LocalDateTime now) {
        this.sender_id = senderId;
        this.recipient_id = recipientId;
        this.content = content;
        this.timestamp = now;
    }

    // Gettery/settery
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSenderId() {
        return sender_id;
    }
    public void setSenderId(Long sender_id) {
        this.sender_id = sender_id;
    }
    public Long getRecipientId() {
        return recipient_id;
    }
    public void setRecipientId(Long recipient_id) {
        this.recipient_id = recipient_id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
