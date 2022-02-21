package com.sparta.juteukki02.juteukki_week02.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
//    @Query("SELECT COUNT(u.email) > 0 FROM User u WHERE u.email = ?1 AND u.password = ?2")
//    boolean existsIdPw(String id, String pw);

    Optional<User> findByUsername(String username);

    Optional<User> findByNickName(String nickname);

    boolean existsByUsernameAndPassword(String username, String password);
}
