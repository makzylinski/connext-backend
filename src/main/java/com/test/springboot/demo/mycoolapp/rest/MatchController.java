package com.test.springboot.demo.mycoolapp.rest;

import com.test.springboot.demo.mycoolapp.entity.Match;
import com.test.springboot.demo.mycoolapp.entity.MessageEntity;
import com.test.springboot.demo.mycoolapp.model.ResponseMessage;
import com.test.springboot.demo.mycoolapp.model.UserResponse;
import com.test.springboot.demo.mycoolapp.repository.MatchRepository;
import com.test.springboot.demo.mycoolapp.repository.MessageRepository;
import com.test.springboot.demo.mycoolapp.repository.UserRepository;
import com.test.springboot.demo.mycoolapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @PostMapping("/accept")
    public ResponseEntity<ResponseMessage> acceptUser(@RequestParam("matchId") Integer matchId) {
        Integer userId = userService.getCurrentUserId();
        Match match = matchRepository.findById(matchId).orElseGet(() -> new Match(new ArrayList<>()));
        match.addToAcceptedList(userId);
        matchRepository.save(match);
        return ResponseEntity.ok(new ResponseMessage("User accepted"));
    }

    @GetMapping("/pairs")
    public List<UserResponse> getMutualLikes() {
        Integer currentUserId = userService.getCurrentUserId();
        Match currentUserMatch = matchRepository.findById(currentUserId).orElse(new Match());
        List<Integer> likedUserIds = currentUserMatch.getAcceptedList();
        List<UserResponse> mutualLikes = new ArrayList<>();

        for (Integer likedUserId : likedUserIds) {
            Match likedUserMatch = matchRepository.findById(likedUserId).orElse(new Match());
            Pageable pageable = PageRequest.of(0, 1, Sort.by("timestamp").descending());
            List<MessageEntity> messages = messageRepository.findMessagesBetweenUsersOrderByTimestampDesc(currentUserId.longValue(), likedUserId.longValue(), pageable );
            MessageEntity lastMessage = messages.isEmpty() ? null : messages.get(0);

            if (likedUserMatch.getAcceptedList().contains(currentUserId)) {
                userRepository.findById(likedUserId).ifPresent(user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setUsername(user.getUsername());
                    response.setEmail(user.getEmail());
                    response.setBio(user.getBio());
                    response.setProfileImageUrl(user.getProfileImageUrl());
                    response.setDateOfBirth(user.getDateOfBirth());
                    response.setRole(user.getRole() != null ? user.getRole().name() : "USER");
                    response.setLatestMessage(lastMessage != null ? lastMessage.getContent() : null);
                    mutualLikes.add(response);
                });
            }
        }

        return mutualLikes;
    }
}
