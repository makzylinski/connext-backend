package com.test.springboot.demo.mycoolapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public Long getId() {
        return id;
    }


}
