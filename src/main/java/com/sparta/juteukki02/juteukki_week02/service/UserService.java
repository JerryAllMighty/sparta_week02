package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.model.User;
import com.sparta.juteukki02.juteukki_week02.model.UserDto;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Long update(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        user.update(userDto);
        return id;
    }
}
