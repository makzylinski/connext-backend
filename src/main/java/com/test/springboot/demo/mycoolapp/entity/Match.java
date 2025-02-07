package com.test.springboot.demo.mycoolapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    private List<Integer> acceptedList = new ArrayList<>();

    public Match(ArrayList<Integer> acceptedList) {
        this.acceptedList = acceptedList;
    }

    public void addToAcceptedList(Integer userId) {
        if (acceptedList.contains(userId)) {
            throw new RuntimeException("User already accepted");
        } else {
            acceptedList.add(userId);
        }
    }
//    addToAcceptedList
}
