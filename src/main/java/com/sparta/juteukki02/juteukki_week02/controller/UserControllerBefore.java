//package com.sparta.juteukki02.juteukki_week02.controller;
//
//
//import com.sparta.juteukki02.juteukki_week02.Dto.SignupRequestDto;
//import com.sparta.juteukki02.juteukki_week02.model.User;
//import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
//import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
//import com.sparta.juteukki02.juteukki_week02.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    // 회원 로그인 페이지
//    @GetMapping("/user/login")
//    public String login() {
//        return "login";
//    }
//    // 회원 가입 페이지
//    @GetMapping("/user/signup")
//    public String signup() {
//        return "signup";
//    }
//}
//
