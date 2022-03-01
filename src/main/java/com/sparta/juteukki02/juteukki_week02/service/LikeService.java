package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.Dto.LikeDto;
import com.sparta.juteukki02.juteukki_week02.model.MyLike;
import com.sparta.juteukki02.juteukki_week02.model.LikeRepository;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostService postService;

    @Transactional
    public String checkLike(LikeDto likeDto){
        // 만약 아직 이 게시글에 좋아요를 안 했다면 +1
        boolean exists = likeRepository.existsByPostIdAndUserId(likeDto.getPostId(), likeDto.getUserId());
        if (!exists) {
            //        게시글 좋아요 +1
            postService.updateLikeCount(likeDto.getPostId());
            //            좋아요 테이블에 생성
            // 빌더 패턴 적용
            MyLike like = MyLike.builder()
                            .postId(likeDto.getPostId())
                                    .userId(likeDto.getUserId())
                                            .build();
            likeRepository.save(like);
            Helper.JSONBuilder builder = new Helper.JSONBuilder();
            builder.addKeyValue("result", "False");
            builder.addKeyValue("msg", "좋아요를 반영했습니다.");
            return builder.build().getReturnJSON();
        }
        // 만약 이미 좋아요를 했다면
        else{
            //        게시글 좋아요 -1
            postService.minusLikeCount(likeDto.getPostId());
            //            좋아요 테이블 삭제
            likeRepository.deleteByPostIdAndUserId(likeDto.getPostId(),likeDto.getUserId());
            Helper.JSONBuilder builder = new Helper.JSONBuilder();
            builder.addKeyValue("result", "False");
            builder.addKeyValue("msg", "좋아요를 취소했습니다.");
            return builder.build().getReturnJSON();
        }

    }
}
