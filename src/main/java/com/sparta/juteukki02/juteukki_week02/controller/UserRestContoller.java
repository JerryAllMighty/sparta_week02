package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.model.User;
import com.sparta.juteukki02.juteukki_week02.model.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.model.UserRegisterDto;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class UserRestContoller {

    private final UserService userService;
    private final UserRepository userRepository;


    @PostMapping("/user")
    public String login2(@RequestBody UserLoginDto userLoginDto) {
        System.out.println("Rest");
        String id = userLoginDto.getUsername();
        String pw = userLoginDto.getPassword();
        return userService.checkLogin(id, pw);
    }

    @PostMapping("/user/signup")
    public String registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        User user = new User(userRegisterDto);
        return userService.checkRegister(user);
    }
}
