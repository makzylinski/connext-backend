package com.test.springboot.demo.mycoolapp.rest;

public class UserDTO {
    private Integer id;
    private String username;
    private String profileImageUrl;


    public UserDTO(Integer id, String username, String profileImageUrl) {
        this.id = id;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
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

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
