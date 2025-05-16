package com.test.springboot.demo.mycoolapp.model;

import java.util.Date;

public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private String role;
    private Date dateOfBirth;
    private String bio;
    private String profileImageUrl;
    private String latestMessage;

    public String getLatestMessage() {
        return latestMessage;
    }
    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}