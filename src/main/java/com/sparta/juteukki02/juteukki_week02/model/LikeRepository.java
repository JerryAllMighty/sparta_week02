package com.sparta.juteukki02.juteukki_week02.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<MyLike, Long> {
    boolean existsByPostIdAndUserId(String PostId, String UserId);

    void deleteByPostIdAndUserId(String PostId, String UserId);

    List<MyLike> findByUserId(String userId);
}
