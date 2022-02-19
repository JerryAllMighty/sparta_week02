package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.model.*;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/api/login")
    public List<User> getPosts(){
        return userRepository.findAll();
    }

    @PostMapping("/api/login")
    public String login(@RequestBody @Valid UserLoginDto idpw){
        User user = new User(idpw);
        String id = user.getAccount();
        String pw = user.getPassword();
        return userService.checkLogin(id, pw);

    }

    @PostMapping("/api/register")
    public String createProduct(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        User user = new User(userRegisterDto);
        return userService.checkRegister(user);
    }
//    }
//    @PutMapping("/api/comments/{id}")
//    public Long updateProduct(@PathVariable Long id, @RequestBody CommentDto commentDto) {
//        return commentservice.update(id, commentDto);
//    }
//
//    @DeleteMapping("/api/comments/{id}")
//    public Long deleteMemo(@PathVariable Long id) {
//        commentRepository.deleteById(id);
//        return id;
//    }

}
