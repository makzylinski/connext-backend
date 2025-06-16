package com.test.springboot.demo.mycoolapp.rest;

import com.test.springboot.demo.mycoolapp.entity.User;
import com.test.springboot.demo.mycoolapp.model.UserResponse;
import com.test.springboot.demo.mycoolapp.repository.UserRepository;
import com.test.springboot.demo.mycoolapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponse> getUsersWithoutPair() {
        Integer currentUserId = userService.getCurrentUserId();
        List<User> users = userRepository.findUsersWithoutPair(currentUserId);
        return users.stream().map(user -> {
            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole() != null ? user.getRole().name() : "USER");
            response.setDateOfBirth(user.getDateOfBirth());
            response.setProfileImageUrl(user.getProfileImageUrl());
            return response;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
    @PostMapping("/uploadProfileImage")
    public ResponseEntity<String> uploadProfileImage(@RequestParam("userId") Integer userId, @RequestParam("file") MultipartFile file) {
        String fileName = saveFile(file);
        String fileUrl = "http://localhost:8080/images/" + fileName;
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setProfileImageUrl(fileUrl);
        userRepository.save(user);
        return ResponseEntity.ok(fileUrl);
    }

    @PostMapping("/add-bio")
    public ResponseEntity<String> addBio(@RequestParam("userId") Integer userId, @RequestParam("bio") String bio) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setBio(bio);
        userRepository.save(user);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("\"" + bio + "\"");
    }

    @PostMapping("/add-birth-date")
    public ResponseEntity<Date> addBirthDate(@RequestBody DateDTO dto) {
        Integer currentUserId = userService.getCurrentUserId();
        User user = userRepository.findById(currentUserId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setDateOfBirth(dto.date);
        userRepository.save(user);
        return ResponseEntity.ok(dto.date);
    }

    @GetMapping("/profileImage")
    public ResponseEntity<String> getProfileImage(@RequestParam("userId") Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user.getProfileImageUrl());
    }

    private String saveFile(MultipartFile file) {
        String uploadDir = "src/main/resources/images/";

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path filePath = Paths.get(uploadDir + fileName);

        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas zapisywania pliku", e);
        }

        return fileName;
    }

}
