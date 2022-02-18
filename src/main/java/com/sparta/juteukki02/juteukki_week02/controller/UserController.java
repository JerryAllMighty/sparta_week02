package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.model.User;
import com.sparta.juteukki02.juteukki_week02.model.UserDto;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/register")
    public User createProduct(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        userRepository.save(user);
        return user;
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
