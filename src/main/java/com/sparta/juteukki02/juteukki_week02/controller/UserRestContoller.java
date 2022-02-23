package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import com.sparta.juteukki02.juteukki_week02.model.*;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

import static com.sparta.juteukki02.juteukki_week02.util.Helper.makeReturnJSON;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class UserRestContoller {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/api/login")
    public void login(@RequestBody @Valid UserLoginDto userLoginDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        한글이 깨지지않게 하기 위한 설정
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //        현재 사용자가 로그인을 했는지 체크
        if (request.getSession(false) != null) {
            writer.print(makeReturnJSON("result", "False", "msg", "이미 로그인이 되어 있습니다."));
            return;
        }

        String id = userLoginDto.getUsername();
        String pw = userLoginDto.getPassword();
        boolean exists = userRepository.existsByUsernameAndPassword(id, pw);

        HttpSession session = request.getSession();
// 입력 정보와 일치하는 정보가 있으면 로그인, 아니면 메세지 출력
        if (exists) {
            User user = userRepository.findByUsername(id).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
            session.setAttribute("userLoginDto", userLoginDto);
            writer.print(makeReturnJSON("result", "True", "msg", "로그인에 성공했습니다.", "username", id, "nickName", user.getNickName()));
        } else {
            writer.print(makeReturnJSON("result", "False", "msg", "닉네임 또는 패스워드를 확인해주세요."));
        }

    }

    @PostMapping("/api/signup")
    public String registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto, HttpServletRequest request) {
// 이미 로그인한 사람이라면 회원가입 안내 메세지 출력
        if (request.getSession(false) != null) {
            return makeReturnJSON("result", "False", "msg", "이미 로그인이 되어 있습니다.");
        }
        User user = new User(userRegisterDto);
        return userService.checkRegister(user);
    }
}
