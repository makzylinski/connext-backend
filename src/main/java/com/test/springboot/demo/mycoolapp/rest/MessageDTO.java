package com.test.springboot.demo.mycoolapp.rest;

import java.time.LocalDateTime;

public class MessageDTO {
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private UserDTO conversationPartner;

    public MessageDTO() {}

    public MessageDTO(Long id, String content, LocalDateTime timestamp, UserDTO conversationPartner) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.conversationPartner = conversationPartner;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserDTO getConversationPartner() {
        return conversationPartner;
    }

    public void setConversationPartner(UserDTO conversationPartner) {
        this.conversationPartner = conversationPartner;
    }

}
