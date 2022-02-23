package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.Dto.LikeDto;
import com.sparta.juteukki02.juteukki_week02.model.MyLike;
import com.sparta.juteukki02.juteukki_week02.model.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.sparta.juteukki02.juteukki_week02.util.Helper.makeReturnJSON;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostService postService;

    @Transactional
    public String checkLike(LikeDto likeDto){
        // 만약 아직 이 게시글에 좋아요를 안 했다면 +1
        boolean exists = likeRepository.existsByPostIdAndUserId(likeDto.getPostId(), likeDto.getUserId());
        if (exists == false) {
            //        게시글 좋아요 +1
            postService.updateLikeCount(Long.parseLong(likeDto.getPostId()));
            //            좋아요 테이블에 생성
            MyLike like = new MyLike(likeDto);
            likeRepository.save(like);
            return makeReturnJSON("result", "False", "msg", "좋아요를 반영했습니다.");
        }
        // 만약 이미 좋아요를 했다면
        else{
            //        게시글 좋아요 -1
            postService.minusLikeCount(Long.parseLong(likeDto.getPostId()));
            //            좋아요 테이블 삭제
            likeRepository.deleteByPostIdAndUserId(likeDto.getPostId(),likeDto.getUserId());
            return makeReturnJSON("result", "True", "msg", "좋아요를 취소했습니다.");
        }

    }
}
