package com.test.springboot.demo.mycoolapp.repository;

import com.test.springboot.demo.mycoolapp.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUsername(String username);
    Profile findByEmail(String email);
}
