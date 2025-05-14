package com.test.springboot.demo.mycoolapp.rest;

import java.time.LocalDateTime;

public class MessageDTO {
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private UserDTO sender;
    private UserDTO recipient;

    public MessageDTO() {}

    public MessageDTO(Long id, String content, LocalDateTime timestamp, UserDTO sender, UserDTO recipient) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.sender = sender;
        this.recipient = recipient;
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

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public UserDTO getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDTO recipient) {
        this.recipient = recipient;
    }


}
