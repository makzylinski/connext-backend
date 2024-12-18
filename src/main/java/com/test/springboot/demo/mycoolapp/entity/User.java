package com.test.springboot.demo.mycoolapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Minimum 3 characters required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Minimum 6 characters required")
    private String password;

    @NotBlank(message = "Email is required")
    @Size(max = 50, message = "Maximum 50 characters allowed")
    @Email(message = "Enter a valid email address")
    private String email;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
