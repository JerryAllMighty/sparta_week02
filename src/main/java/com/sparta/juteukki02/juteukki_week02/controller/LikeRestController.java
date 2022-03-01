package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.LikeDto;
import com.sparta.juteukki02.juteukki_week02.jwt.JwtTokenProvider;
import com.sparta.juteukki02.juteukki_week02.model.MyLike;
import com.sparta.juteukki02.juteukki_week02.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class LikeRestController {
    private final LikeService likeService;
    
    @PostMapping("/api/like")
    public String postLike(@RequestBody LikeDto likeDto){
        MyLike like = new MyLike();
        //넘겨받은 값이 nulL이거나, 기준 미달인지 체크
        String valadationCheck = like.isValidLike(likeDto);
        if (valadationCheck.equals("success"))
            return likeService.checkLike(likeDto);
        else
            return valadationCheck;
    }
}
