package com.test.springboot.demo.mycoolapp.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @ElementCollection
    private List<Integer> acceptedList = new ArrayList<>();

    public Match() {
    }

    public Match(List<Integer> acceptedList) {
        this.acceptedList = acceptedList;
    }

    public void addToAcceptedList(Integer userId) {
        if (acceptedList.contains(userId)) {
            throw new RuntimeException("User already accepted");
        } else {
            acceptedList.add(userId);
        }
    }

    public List<Integer> getAcceptedList() {
        return acceptedList;
    }

    public Integer getId() {
        return userId;
    }

    public void setId(Integer id) {
        this.userId = id;
    }
}