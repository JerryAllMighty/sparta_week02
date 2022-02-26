package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.LikeDto;
import com.sparta.juteukki02.juteukki_week02.jwt.JwtTokenProvider;
import com.sparta.juteukki02.juteukki_week02.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.sparta.juteukki02.juteukki_week02.util.Helper.makeReturnJSON;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class LikeRestController {
    private final LikeService likeService;
    private final JwtTokenProvider jwtTokenProvider;
    
    @PostMapping("/api/like")
    public String postLike(@RequestBody @Valid LikeDto likeDto, HttpServletRequest request){
//        현재 세션이 유용한지 체크
        String header = jwtTokenProvider.resolveToken(request);
        if (!jwtTokenProvider.validateToken(header))
        {
            return "Token Non";
        }
        return likeService.checkLike(likeDto);
    }
}
