package com.test.springboot.demo.mycoolapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId1;
    private Long userId2;
}
