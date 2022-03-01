package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.PostDeleteDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostRegisterDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostGetDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import com.sparta.juteukki02.juteukki_week02.jwt.JwtTokenProvider;
import com.sparta.juteukki02.juteukki_week02.model.*;
import com.sparta.juteukki02.juteukki_week02.service.LikeService;
import com.sparta.juteukki02.juteukki_week02.service.PostService;
import com.sparta.juteukki02.juteukki_week02.service.UserService;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostRestController {
    private final LikeRepository likeRepository;

    private final PostService postService;
    private final PostRepository postRepository;

    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/api/showpost")
    public String getPosts( @RequestBody PostGetDto postGetDto) {

        List<Post> posts = postRepository.findAll();
        List<MyLike> likes = likeRepository.findByUserId(postGetDto.getUserId());
        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValueList("total", posts);
        builder.addKeyValueList("myLike", likes);
        return builder.build().getReturnJSON();

    }
    @PostMapping("/api/post")
    public String addPosts(@AuthenticationPrincipal User user,@RequestBody PostRegisterDto postDto, HttpServletRequest request) {

        String header = jwtTokenProvider.resolveToken(request);
        if (!jwtTokenProvider.validateToken(header))
        {
            return "Invalid Token";
        }
        return postService.uploadPost(postDto);
    }
    @PutMapping("/api/post")
    public String updatePosts(@RequestBody PostUpdateDto postUpdateDto, HttpServletRequest request) {
        String header = jwtTokenProvider.resolveToken(request);
        if (!jwtTokenProvider.validateToken(header))
        {
            return "Token Non";
        }
        postService.update(postUpdateDto);
        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "True");
        builder.addKeyValue("msg","게시글 수정 완료되었습니다.");
        return builder.build().getReturnJSON();
    }
    @DeleteMapping("/api/post")
    public String deletePosts(@RequestBody PostDeleteDto postDeleteDto, HttpServletRequest request) {
        String header = jwtTokenProvider.resolveToken(request);
        if (!jwtTokenProvider.validateToken(header))
        {
            return "Token Non";
        }
        postRepository.deleteById(Long.parseLong(postDeleteDto.getPostId()));
        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "True");
        builder.addKeyValue("msg","게시글 삭제 완료되었습니다.");
        return builder.build().getReturnJSON();
    }
}
