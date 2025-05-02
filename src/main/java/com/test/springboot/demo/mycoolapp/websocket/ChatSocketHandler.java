package com.test.springboot.demo.mycoolapp.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springboot.demo.mycoolapp.entity.MessageEntity;
import com.test.springboot.demo.mycoolapp.model.ChatMessage;
import com.test.springboot.demo.mycoolapp.repository.MessageRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChatSocketHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String username = getUsernameFromSession(session);
        if (username != null) {
            userSessions.put(username, session);
        }
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);

        MessageEntity entity = new MessageEntity(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                chatMessage.getContent(),
                LocalDateTime.now()
        );
        messageRepository.save(entity);

        for (WebSocketSession sess : sessions) {
            if (sess.isOpen()) {
                sess.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String username = getUsernameFromSession(session);
        if (username != null) {
            userSessions.remove(username);
        }
        sessions.remove(session);
    }

    private String getUsernameFromSession(WebSocketSession session) {
        try {
            URI uri = session.getUri();
            if (uri != null) {
                String query = uri.getQuery();
                if (query != null && query.startsWith("user=")) {
                    return URLDecoder.decode(query.substring(5), StandardCharsets.UTF_8);
                }
            }
        } catch (Exception ignored) {}
        return null;
    }
}
