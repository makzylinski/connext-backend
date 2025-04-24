package com.test.springboot.demo.mycoolapp.rest;

import com.test.springboot.demo.mycoolapp.entity.Match;
import com.test.springboot.demo.mycoolapp.entity.User;
import com.test.springboot.demo.mycoolapp.model.ResponseMessage;
import com.test.springboot.demo.mycoolapp.repository.MatchRepository;
import com.test.springboot.demo.mycoolapp.repository.UserRepository;
import com.test.springboot.demo.mycoolapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> getMutualLikes() {
        Integer currentUserId = userService.getCurrentUserId();
        Match currentUserMatch = matchRepository.findById(currentUserId).orElse(new Match());
        List<Integer> likedUserIds = currentUserMatch.getAcceptedList();
        List<User> mutualLikes = new ArrayList<>();

        for (Integer likedUserId : likedUserIds) {
            Match likedUserMatch = matchRepository.findById(likedUserId).orElse(new Match());
            if (likedUserMatch.getAcceptedList().contains(currentUserId)) {
                userRepository.findById(likedUserId).ifPresent(mutualLikes::add);
            }
        }

        return mutualLikes;
    }
}
