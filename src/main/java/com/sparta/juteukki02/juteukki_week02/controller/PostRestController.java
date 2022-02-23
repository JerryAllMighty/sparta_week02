package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.PostDeleteDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostRegisterDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostGetDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import com.sparta.juteukki02.juteukki_week02.model.*;
import com.sparta.juteukki02.juteukki_week02.service.LikeService;
import com.sparta.juteukki02.juteukki_week02.service.PostService;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostRestController {
    private final UserService userService;
    private final UserRepository userRepository;

    private final LikeService likeService;
    private final LikeRepository likeRepository;

    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/api/post")
    public String getPosts(@RequestBody PostGetDto postGetDto) {
        List<Post> posts = postRepository.findAll();
        List<MyLike> likes = likeRepository.findByUserId(postGetDto.getUsername());
        return Helper.makeReturnJSON("total", posts,"myLike",likes);

    }
    @PostMapping("/api/post")
    public String addPosts(@RequestBody @Valid PostRegisterDto postDto, HttpServletRequest request) {
        if (request.getSession(false) == null){
            return Helper.makeReturnJSON("result", "False","msg","로그인이 필요합니다.");
        }
        Post post = new Post(postDto);
        postRepository.save(post);
        return Helper.makeReturnJSON("result", "True","msg","게시글 등록이 완료되었습니다.");
    }
    @PutMapping("/api/post")
    public String updatePosts(@RequestBody @Valid PostUpdateDto postUpdateDto, HttpServletRequest request) {
        if (request.getSession(false) == null){
            return Helper.makeReturnJSON("result", "False","msg","로그인이 필요합니다.");
        }
        postService.update(postUpdateDto);
        return Helper.makeReturnJSON("result", "True","msg","게시글 수정 완료되었습니다.");
    }
    @DeleteMapping("/api/post")
    public String deletePosts(@RequestBody @Valid PostDeleteDto postDeleteDto, HttpServletRequest request) {
        if (request.getSession(false) == null){
            return Helper.makeReturnJSON("result", "False","msg","로그인이 필요합니다.");
        }
        postRepository.deleteById(Long.parseLong(postDeleteDto.getPostId()));
        return Helper.makeReturnJSON("result", "True","msg","게시글 삭제 완료되었습니다.");
    }
}
