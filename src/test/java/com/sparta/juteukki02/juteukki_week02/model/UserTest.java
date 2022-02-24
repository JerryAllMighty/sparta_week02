package com.sparta.juteukki02.juteukki_week02.model;

import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Nested
    @DisplayName("회원 로그인 테스트")
    class UserLoginTest {
        Long userId;
        String username;
        String password;
        String nickName;

        @BeforeEach
        void setup() {
            username = "test@naver.com";
            password = "1234";
            nickName = "테스트용 유저";
        }


        @Test
        @DisplayName("정상 케이스")
        void LoginUser_Normal() {
            UserLoginDto userLoginDto = new UserLoginDto();
            userLoginDto.setUsername(username);
            userLoginDto.setPassword(password);

// when
            User user = new User(userLoginDto);

// then
            assertEquals(username, user.getUsername());
            assertEquals(password, user.getPassword());
        }

        @Nested
        @DisplayName("실패 케이스")
        class LoginUser_Fail{
            @Nested
            @DisplayName("회원 Id")
            class userId {
                @Test
                @DisplayName("null")
                void fail1() {
// when
//                    Exception exception = assertThrows(MethodArgumentNotValidException.class, () -> {
//                        new UserLoginDto();
//                    });
//
//// then
//                    assertEquals("필수 값이 누락되었습니다.", exception.getMessage());
                }
            }
        }
    }
}