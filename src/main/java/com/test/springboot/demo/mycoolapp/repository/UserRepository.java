package com.test.springboot.demo.mycoolapp.repository;

import com.test.springboot.demo.mycoolapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
