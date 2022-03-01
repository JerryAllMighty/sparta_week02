package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import com.sparta.juteukki02.juteukki_week02.model.*;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
@RequestMapping("/api")
public class UserContoller {

    private final UserService userService;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/register")
    public String join(@RequestBody UserRegisterDto userRegisterDto) {
        User user = new User();
        //넘겨받은 값이 nulL이거나, 기준 미달인지 체크
        String valadationCheck = user.isValidRegister(userRegisterDto);
        if (valadationCheck.equals("success"))
            return userService.checkRegister(userRegisterDto);
        else
            return valadationCheck;
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) {
        User user = new User();
        //넘겨받은 값이 nulL이거나, 기준 미달인지 체크
        String valadationCheck = user.isValidLogin(userLoginDto);
        if (valadationCheck.equals("success"))
            return userService.checkLogin(userLoginDto);
        else
            return valadationCheck;

    }
    // 로그아웃
    @PostMapping("/logout")
    public String login(HttpServletRequest request) {
        return userService.checkLogOut(request);
    }
}
