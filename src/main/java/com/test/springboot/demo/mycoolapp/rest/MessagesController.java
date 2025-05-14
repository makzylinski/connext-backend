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
import java.util.stream.Collectors;

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
    public List<MessageDTO> getMessages(@PathVariable Long userId) {
        Long currentUserId = Long.valueOf(userService.getCurrentUserId());
        List<MessageEntity> messages = messageRepository.findConversationsBetweenUsers(userId, currentUserId);

        UserDTO conversationPartner = userService.getUserDTOById(userId);

        return messages.stream().map(message ->
                new MessageDTO(
                        message.getId(),
                        message.getContent(),
                        message.getTimestamp(),
                        conversationPartner
                )
        ).collect(Collectors.toList());
    }
}
