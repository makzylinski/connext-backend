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
        Integer currentUserId = userService.getCurrentUserId();
        List<MessageEntity> messages = messageRepository.findConversationsBetweenUsers(userId, Long.valueOf(currentUserId));

        return messages.stream().map(message -> {
            UserDTO sender = userService.getUserDTOById(message.getSenderId());
            UserDTO recipient = userService.getUserDTOById(message.getRecipientId());

            return new MessageDTO(
                    message.getId(),
                    message.getContent(),
                    message.getTimestamp(),
                    sender,
                    recipient
            );
        }).collect(Collectors.toList());
    }
}
