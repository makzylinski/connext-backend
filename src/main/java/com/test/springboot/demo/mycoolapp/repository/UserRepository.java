package com.test.springboot.demo.mycoolapp.repository;

import com.test.springboot.demo.mycoolapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id != :currentUserId AND u.id NOT IN " +
            "(SELECT m.userId FROM Match m WHERE :currentUserId MEMBER OF m.acceptedList)")
    List<User> findUsersWithoutPair(@Param("currentUserId") Integer currentUserId);
}
