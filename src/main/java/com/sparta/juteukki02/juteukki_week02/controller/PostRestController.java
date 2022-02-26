package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.PostDeleteDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostRegisterDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostGetDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import com.sparta.juteukki02.juteukki_week02.jwt.JwtTokenProvider;
import com.sparta.juteukki02.juteukki_week02.model.*;
import com.sparta.juteukki02.juteukki_week02.security.UserDetailsImpl;
import com.sparta.juteukki02.juteukki_week02.service.LikeService;
import com.sparta.juteukki02.juteukki_week02.service.PostService;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/api/showpost")
    public String getPosts(@RequestBody PostGetDto postGetDto) {
        List<Post> posts = postRepository.findAll();
        List<MyLike> likes = likeRepository.findByUserId(postGetDto.getUserId());
        return Helper.makeReturnJSONList("total", posts,"myLike",likes);

    }
    @PostMapping("/api/post")
    public String addPosts(@RequestBody PostRegisterDto postDto, HttpServletRequest request) {
        String header = jwtTokenProvider.resolveToken(request);
        if (!jwtTokenProvider.validateToken(header))
        {
            return "Token Non";
        }

        Post post = new Post(postDto);
//        postService.insertWithQuery(post);
        postRepository.save(post);
        return Helper.makeReturnJSON("result", "True","msg","게시글 등록이 완료되었습니다.");
    }
    @PutMapping("/api/post")
    public String updatePosts(@RequestBody PostUpdateDto postUpdateDto, HttpServletRequest request) {
        String header = jwtTokenProvider.resolveToken(request);
        if (!jwtTokenProvider.validateToken(header))
        {
            return "Token Non";
        }
        postService.update(postUpdateDto);
        return Helper.makeReturnJSON("result", "True","msg","게시글 수정 완료되었습니다.");
    }
    @DeleteMapping("/api/post")
    public String deletePosts(@RequestBody PostDeleteDto postDeleteDto, HttpServletRequest request) {
        String header = jwtTokenProvider.resolveToken(request);
        if (!jwtTokenProvider.validateToken(header))
        {
            return "Token Non";
        }
        postRepository.deleteById(Long.parseLong(postDeleteDto.getPostId()));
        return Helper.makeReturnJSON("result", "True","msg","게시글 삭제 완료되었습니다.");
    }
}
