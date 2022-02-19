package com.sparta.juteukki02.juteukki_week02.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT COUNT(u.account) > 0 FROM User u WHERE u.account = ?1 AND u.password = ?2")
    boolean existsIdPw(String id, String pw);
}
