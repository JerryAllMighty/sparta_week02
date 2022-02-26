//package com.sparta.juteukki02.juteukki_week02.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.juteukki02.juteukki_week02.model.User;
//import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
//import org.json.JSONObject;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//    @Mock
//    UserRepository userRepository;
//
//    Long id = 100L;
//    String username = "asd";
//    String password = "1234";
//    String passwordcheck = "1234";
//    String nickName = "Pizza";
//
//    @Test
//    @DisplayName("회원 가입 유효성 체크")
//    void isValid_RegisterUser_Normal() {
//// given
//        User user = new User();
////        user.setId(id);
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setPasswordCheck(passwordcheck);
//        user.setNickName(nickName);
//
//        UserService userService = new UserService(userRepository);
//
//// when
//        String result = userService.checkRegister(user);
//
//// then
//        assertThat(result).doesNotContain("닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)입니다.");
//        assertThat(result).doesNotContain("비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함될 수 없습니다.");
//        assertThat(result).doesNotContain("비밀번호 일치 여부를 확인해주세요.");
//    }
//}