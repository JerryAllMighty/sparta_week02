package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.LikeDto;
import com.sparta.juteukki02.juteukki_week02.model.LikeRepository;
import com.sparta.juteukki02.juteukki_week02.model.MyLike;
import com.sparta.juteukki02.juteukki_week02.model.PostRepository;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
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
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class LikeRestController {
    private final UserService userService;
    private final UserRepository userRepository;

    private final LikeService likeService;
    private final LikeRepository likeRepository;

    private final PostService postService;
    private final PostRepository postRepository;

    @Transactional
    @PostMapping("/api/like")
    public String postLike(@RequestBody @Valid LikeDto likeDto, HttpServletRequest request){
//        현재 사용자가 로그인을 했는지 체크
        HttpSession session = request.getSession(false);
//        session.invalidate();
        JSONObject obj = new JSONObject();

        if (session == null){
            obj.append("result", "False");
            obj.append("msg", "로그인이 필요합니다.");
            return (obj.toString());
        }else{
//            게시글 좋아요 +1
//            좋아요 테이블에 생성
            // 만약 아직 이 게시글에 좋아요를 안 했다면 +1
            boolean exists = likeRepository.existsByPostIdAndUserId(likeDto.getPostId(), likeDto.getUserId());
            System.out.println(exists);
            if (exists == false) {
                postService.updateLikeCount(Long.parseLong(likeDto.getPostId()));
                // 그후 LIKE 테이블에 저장
                MyLike like = new MyLike(likeDto);
                likeService.saveLike(like);
                obj.append("result", "False");
                obj.append("msg", "");
                return (obj.toString());
            }else{
                postService.minusLikeCount(Long.parseLong(likeDto.getPostId()));
                likeRepository.deleteByPostIdAndUserId(likeDto.getPostId(),likeDto.getUserId());
                obj.append("result", "True");
                obj.append("msg", "");
                return (obj.toString());
            }



        }

    }
}
