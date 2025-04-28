package com.test.springboot.demo.mycoolapp.model;

public class ChatMessage {
    private Long sender_id;
    private Long recipient_id;
    private String content;

    public ChatMessage() {}

    public ChatMessage(Long sender_id, Long recipient_id, String content) {
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.content = content;
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
}
