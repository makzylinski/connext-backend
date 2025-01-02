package com.test.springboot.demo.mycoolapp.rest;

import com.test.springboot.demo.mycoolapp.entity.User;
import com.test.springboot.demo.mycoolapp.model.Role;
import com.test.springboot.demo.mycoolapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getRole() == null) {
                user.setRole(Role.USER);
            }
        }
        return users;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
}
