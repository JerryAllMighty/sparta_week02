package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.LikeDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import com.sparta.juteukki02.juteukki_week02.model.*;
import com.sparta.juteukki02.juteukki_week02.service.LikeService;
import com.sparta.juteukki02.juteukki_week02.service.PostService;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class UserRestContoller {

    private final UserService userService;
    private final UserRepository userRepository;

    private final LikeService likeService;
    private final LikeRepository likeRepository;

    private final PostService postService;
    private final PostRepository postRepository;


    @PostMapping("/api/login")
    public void login(@RequestBody @Valid UserLoginDto userLoginDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.getSession().invalidate();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();

        JSONObject obj = new JSONObject();
        //        현재 사용자가 로그인을 했는지 체크
        if (request.getSession(false) != null){
            obj.append("result", "False");
            obj.append("msg", "이미 로그인이 되어 있습니다.");
            writer.print(obj.toString());
            return;
        }

        String id = userLoginDto.getUsername();
        String pw = userLoginDto.getPassword();
        boolean exists = userRepository.existsByUsernameAndPassword(id, pw);

        HttpSession session = request.getSession();

        if (exists){
            obj.append("result", "True");
            obj.append("msg", "로그인에 성공했습니다.");
            obj.append("username", id);
            User user = userRepository.findByUsername(id).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
            obj.append("nickName", user.getNickName());
            session.setAttribute("userLoginDto", userLoginDto);
            writer.print(obj.toString());
        }else{
            obj.append("result", "False");
            obj.append("msg", "닉네임 또는 패스워드를 확인해주세요.");
            writer.print(obj.toString());
        }

    }

    @PostMapping("/api/signup")
    public String registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        JSONObject obj = new JSONObject();
        if (session != null){
            obj.append("result", "False");
            obj.append("msg", "이미 로그인이 되어 있습니다.");
            return obj.toString();

        }
        User user = new User(userRegisterDto);
        return userService.checkRegister(user);
    }


}
