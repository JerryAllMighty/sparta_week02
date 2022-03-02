package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.Dto.PostGetDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostRegisterDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import com.sparta.juteukki02.juteukki_week02.model.LikeRepository;
import com.sparta.juteukki02.juteukki_week02.model.MyLike;
import com.sparta.juteukki02.juteukki_week02.model.Post;
import com.sparta.juteukki02.juteukki_week02.model.PostRepository;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    //전체 게시글 조회와, 사용자가 좋아요를 표시한 게시글 목록은 각각 캐시에 저장하여 사용
    @Cacheable(value = "post")
    public List<Post> getTotal(String key) {
        // 아직 캐시에 정보가 저장되지 않았다면, 첫 1회만 전체 게시글 조회 후 저장
        System.out.println("This means it's the first time saving TotalPosts cache.");
        return postRepository.findAll();

    }
    @Cacheable(value = "my_like")
    public List<MyLike> getMyLike(Long userId) {
        // 아직 캐시에 정보가 저장되지 않았다면, 첫 1회만 좋아요한 게시글 조회 후 저장
        System.out.println("This means it's the first time saving MyLike cache.");
        return likeRepository.findByUserId(userId);

    }
    //게시글 전체 조회 결과 삭제, 그래야 DB 수정 결과가 캐시에 반영이 됨.
    @CacheEvict(value = "post", allEntries = true)
    public void updatePost() {
    }//좋아요 게시글 조회 결과 삭제, 그래야 DB 수정 결과가 캐시에 반영이 됨.
    @CacheEvict(value = "my_like", allEntries = true)
    public void updateLike() {
    }

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
