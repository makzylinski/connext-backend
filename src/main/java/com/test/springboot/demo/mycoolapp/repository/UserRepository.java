package com.test.springboot.demo.mycoolapp.repository;

import com.test.springboot.demo.mycoolapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    User findByEmail(String email);
}
