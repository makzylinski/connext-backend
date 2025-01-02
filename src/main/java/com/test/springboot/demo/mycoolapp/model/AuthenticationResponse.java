package com.test.springboot.demo.mycoolapp.model;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationResponse {
    private String token;
    private UserDetails userDetails;

    public AuthenticationResponse(String token, UserDetails userDetails) {
        this.token = token;
        this.userDetails = userDetails;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }
}
