package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.Dto.PostRegisterDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import com.sparta.juteukki02.juteukki_week02.model.Post;
import com.sparta.juteukki02.juteukki_week02.model.PostRepository;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    //게시글 등록
    public String uploadPost(PostRegisterDto postRegisterDto){
        // 빌더 패턴 적용
        Post post = Post.builder()
                .nickName(postRegisterDto.getNickName())
                .contents(postRegisterDto.getContents())
                .image(postRegisterDto.getImage())
                .type(postRegisterDto.getType())
                .likeCount(0)
                .build();
        postRepository.save(post);
        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "True");
        builder.addKeyValue("msg","게시글 등록이 완료되었습니다.");
        return builder.build().getReturnJSON();
    }


// 게시글 수정
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public void update(PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postUpdateDto.getPostId()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.update(postUpdateDto);
    }


    //게시글 좋아요 더하기
    @Transactional
    public void updateLikeCount(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.updateLikeCount();
    }
//게시글 좋아요 빼기
    @Transactional
    public void minusLikeCount(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.minusLikeCount();
    }
}
