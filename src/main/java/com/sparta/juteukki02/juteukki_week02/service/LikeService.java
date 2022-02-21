package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.model.MyLike;
import com.sparta.juteukki02.juteukki_week02.model.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public void saveLike(MyLike like){
        likeRepository.save(like);
    }
}
