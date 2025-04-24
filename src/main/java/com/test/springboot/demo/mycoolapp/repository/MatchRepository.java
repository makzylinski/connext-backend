package com.test.springboot.demo.mycoolapp.repository;

import com.test.springboot.demo.mycoolapp.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {
    @Query("SELECT m FROM Match m WHERE :userId MEMBER OF m.acceptedList AND m.userId IN :likedUserIds")
    List<Match> findMutualLikes(@Param("userId") Integer userId, @Param("likedUserIds") List<Integer> likedUserIds);
}
