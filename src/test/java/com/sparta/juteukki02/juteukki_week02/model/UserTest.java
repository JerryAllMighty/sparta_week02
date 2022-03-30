package com.sparta.juteukki02.juteukki_week02.model;

import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    Long userId;
    String username = "test@naver.com";
    String password = "1234";
    String nickName = "테스트용 유저";
    
//    @Nested
//    @DisplayName("회원 로그인 테스트")
//    class UserLoginTest {
//        @Test
//        @DisplayName("정상 케이스")
//        void LoginUser_Normal() {
//            UserLoginDto userLoginDto = new UserLoginDto();
//            userLoginDto.setUsername(username);
//            userLoginDto.setPassword(password);
//
//            User user = new User();
//
//// when
//            String validationCheck = user.isValidLogin(userLoginDto);
//
//// then
//            assertThat(validationCheck, containsString("success"));
//        }
//
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
//                    assertThat(validationCheck, containsString("비밀번호는 필수 입력입니다."));
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
//                    assertThat(validationCheck, containsString("아이디는 필수 입력입니다."));
//                }
//
//            }
//        }
//    }

//    @Nested
//    @DisplayName("회원 회원가입 테스트")
//    class UserRegisterTest {
//
//        @Test
//        @DisplayName("정상 케이스")
//        void LoginUser_Normal() {
//            UserRegisterDto userRegisterDto = new UserRegisterDto();
//            userRegisterDto.setUsername(username);
//            userRegisterDto.setPassword(password);
//            userRegisterDto.setPasswordCheck(password);
//            userRegisterDto.setNickName(nickName);
//
//            User user = new User();
//// when
//            String validationCheck = user.isValidRegister(userRegisterDto);
//
//// then
//            assertThat(validationCheck, containsString("success"));
//        }
//
//        @Nested
//        @DisplayName("실패 케이스")
//        class LoginUser_Fail{
//            @Nested
//            @DisplayName("회원 아이디")
//            class UserRegisterId {
//                @Test
//                @DisplayName("null이거나 빈 문자열")
//                void fail1() {
//// when
//                    UserRegisterDto userRegisterDto = new UserRegisterDto();
//                    userRegisterDto.setPassword(password);
//                    userRegisterDto.setPasswordCheck(password);
//                    userRegisterDto.setNickName(nickName);
//
//                    User user = new User();
//                    String validationCheck = user.isValidRegister(userRegisterDto);
//// then
//                    assertThat(validationCheck, containsString("아이디는 필수 입력입니다."));
//                }
//
//            }
//            @Nested
//            @DisplayName("회원 비밀번호")
//            class UserRegisterPassword {
//                @Test
//                @DisplayName("null이거나 빈 문자열")
//                void fail1() {
//// when
//                    UserRegisterDto userRegisterDto = new UserRegisterDto();
//                    userRegisterDto.setUsername(username);
//                    userRegisterDto.setPasswordCheck(password);
//                    userRegisterDto.setNickName(nickName);
//
//                    User user = new User();
//                    String validationCheck = user.isValidRegister(userRegisterDto);
//// then
//                    assertThat(validationCheck, containsString("비밀번호는 필수 입력입니다."));
//                }
//
//            }
//            @Nested
//            @DisplayName("회원 비밀번호 확인")
//            class UserRegisterPasswordCheck {
//                @Test
//                @DisplayName("null이거나 빈 문자열")
//                void fail1() {
//// when
//                    UserRegisterDto userRegisterDto = new UserRegisterDto();
//                    userRegisterDto.setPassword(password);
//                    userRegisterDto.setUsername(username);
//                    userRegisterDto.setNickName(nickName);
//
//                    User user = new User();
//                    String validationCheck = user.isValidRegister(userRegisterDto);
//// then
//                    assertThat(validationCheck, containsString("비밀번호 확인은 필수 입력입니다."));
//                }
//
//            }
//            @Nested
//            @DisplayName("회원 닉네임")
//            class UserRegisterNickName {
//                @Test
//                @DisplayName("null이거나 빈 문자열")
//                void fail1() {
//// when
//                    UserRegisterDto userRegisterDto = new UserRegisterDto();
//                    userRegisterDto.setPassword(password);
//                    userRegisterDto.setUsername(username);
//                    userRegisterDto.setPasswordCheck(password);
//
//                    User user = new User();
//                    String validationCheck = user.isValidRegister(userRegisterDto);
//// then
//                    assertThat(validationCheck, containsString("별명은 필수 입력입니다."));
//                }
//
//            }
//        }
//    }
}