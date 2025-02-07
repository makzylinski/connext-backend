package com.test.springboot.demo.mycoolapp.rest;

import com.test.springboot.demo.mycoolapp.entity.Match;
import com.test.springboot.demo.mycoolapp.repository.MatchRepository;
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
    private UserService userService;
    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @PostMapping("/accept")
    public ResponseEntity<String> acceptUser(@RequestParam("matchId") Integer matchId) {
        Integer userId = userService.getCurrentUserId();
//        Match match = matchRepository.findById(matchId);
        Match match = matchRepository.findById(matchId).orElseGet(() -> new Match(new ArrayList<>()));
        match.addToAcceptedList(userId);
        matchRepository.save(match);
        return ResponseEntity.ok("User accepted");
    }

}
