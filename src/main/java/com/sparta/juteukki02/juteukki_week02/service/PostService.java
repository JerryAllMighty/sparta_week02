package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import com.sparta.juteukki02.juteukki_week02.model.Post;
import com.sparta.juteukki02.juteukki_week02.model.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public void update(PostUpdateDto postUpdateDto) {
        System.out.println(postUpdateDto.getPostId());
        Post post = postRepository.findById(Long.parseLong(postUpdateDto.getPostId())).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.update(postUpdateDto);
    }


    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Long updateLikeCount(Long id) {
        System.out.println(id);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.updateLikeCount();
        return id;
    }

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Long minusLikeCount(Long id) {
        System.out.println(id);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.minusLikeCount();
        return id;
    }
}
