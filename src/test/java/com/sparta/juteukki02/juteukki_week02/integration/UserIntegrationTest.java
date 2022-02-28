package com.sparta.juteukki02.juteukki_week02.integration;
import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.model.User;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserIntegrationTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    Long id = 100L;
    String username = "abcd";
    String password = "1234";
    String passwordcheck = "1234";
    String nickName = "Cheese";

    @Nested
    @DisplayName("회원 로그인 테스트")
    class UserLoginTest {
        @Test
        @DisplayName("실패 케이스")
        void LoginUser_Normal() {
            UserLoginDto userLoginDto = new UserLoginDto();
            userLoginDto.setUsername("zxcv");
            userLoginDto.setPassword(password);


        }

//        @Nested
//        @DisplayName("실패 케이스")
//        class LoginUser_Fail{
//            @Nested
//            @DisplayName("회원 아이디")
//            class UserId {
//                @Test
//                @DisplayName("null이거나 빈 문자열")
//                void fail1() {
//// when
//                    UserLoginDto userLoginDto = new UserLoginDto();
//                    userLoginDto.setUsername(password);
//
//                    User user = new User();
//                    String validationCheck = user.isValidLogin(userLoginDto);
//// then
//                    MatcherAssert.assertThat(validationCheck, containsString("비밀번호는 필수 입력입니다."));
//                }
//
//            }
//            @Nested
//            @DisplayName("회원 비밀번호")
//            class Password {
//                @Test
//                @DisplayName("null이거나 빈 문자열")
//                void fail1() {
//// when
//                    UserLoginDto userLoginDto = new UserLoginDto();
//                    userLoginDto.setPassword(password);
//
//                    User user = new User();
//                    String validationCheck = user.isValidLogin(userLoginDto);
//// then
//                    MatcherAssert.assertThat(validationCheck, containsString("아이디는 필수 입력입니다."));
//                }
//
//            }
//        }
    }

//    @Test
//    @DisplayName("회원가입시 중복된 아이디, 닉네임 체크")
//    void test1() {
//        // given
//        User user = new User();
////        user.setId(id);
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setPasswordCheck(passwordcheck);
//        user.setNickName(nickName);
//
//// when
//        String result = userService.checkRegister(user);
//
//// then
//        assertThat(result).doesNotContain("중복된 사용자 ID 가 존재합니다.");
//        assertThat(result).doesNotContain("중복된 사용자 닉네임이 존재합니다.");
//    }


    @Test
    @Order(2)
    @DisplayName("로그인 ")
    void test2() {
        // user id로 값을 찾아오지 못했을 때
        Exception exception = assertThrows(NullPointerException.class, () -> userRepository.findByUsername(username).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다.")));
        assertEquals("해당 아이디가 존재하지 않습니다.", exception.getMessage());
    }

}
