package com.sparta.juteukki02.juteukki_week02.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByNickName(String nickname);

    Optional<User> findById(Long id);
}
