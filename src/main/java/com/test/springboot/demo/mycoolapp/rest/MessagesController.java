package com.test.springboot.demo.mycoolapp.rest;

import com.test.springboot.demo.mycoolapp.entity.MessageEntity;
import com.test.springboot.demo.mycoolapp.repository.MessageRepository;
import com.test.springboot.demo.mycoolapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    @Autowired
    private UserService userService;
    private final MessageRepository messageRepository;

    public MessagesController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/{userId}")
    public List<MessageEntity> getMessages(@PathVariable Long userId) {
        Integer currentUserId = userService.getCurrentUserId();
        return messageRepository.findConversationsBetweenUsers(userId, Long.valueOf(currentUserId));
    }
}
