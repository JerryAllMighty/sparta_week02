package com.sparta.juteukki02.juteukki_week02.integration;
import com.sparta.juteukki02.juteukki_week02.model.User;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

//    @Test
//    @Order(1)
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
