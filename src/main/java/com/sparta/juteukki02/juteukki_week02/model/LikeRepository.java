package com.sparta.juteukki02.juteukki_week02.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeRepository extends JpaRepository<MyLike, Long> {
    boolean existsByPostIdAndUserId(Long PostId, Long UserId);

    void deleteByPostIdAndUserId(Long PostId, Long UserId);

    void deleteByPostId(Long PostId);

    List<MyLike> findByUserId(Long userId);
}
