package com.test.springboot.demo.mycoolapp.service;

import com.test.springboot.demo.mycoolapp.entity.User;
import com.test.springboot.demo.mycoolapp.repository.UserRepository;
import com.test.springboot.demo.mycoolapp.rest.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
            if (user != null) {
                return user.getId();
            } else {
                throw new RuntimeException("User " + userDetails.getUsername() + " not found " );
            }
        }
        throw new RuntimeException("User not authenticated");
    }

    public UserDTO getUserDTOById(Long userId) {
        User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new RuntimeException("User not found"));

        return new UserDTO(
            user.getId(),
            user.getUsername(),
                user.getProfileImageUrl()
        );
    }
}