package com.test.springboot.demo.mycoolapp.rest;

import com.test.springboot.demo.mycoolapp.entity.User;
import com.test.springboot.demo.mycoolapp.model.UserResponse;
import com.test.springboot.demo.mycoolapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
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
    @GetMapping("/profileImage")
    public ResponseEntity<String> getProfileImage(@RequestParam("userId") Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user.getProfileImageUrl());
    }

    private String saveFile(MultipartFile file) {
        String uploadDir = "assets/";

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
