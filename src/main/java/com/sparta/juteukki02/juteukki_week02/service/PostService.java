package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import com.sparta.juteukki02.juteukki_week02.model.Post;
import com.sparta.juteukki02.juteukki_week02.model.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public void insertWithQuery(Post post) {
//        entityManager.createNativeQuery("INSERT INTO post (id, nick_name, contents, image, like_count, type) VALUES (?,?,?,?,?,?)")
//                .setParameter(1, post.getId())
//                .setParameter(2, post.getNickName())
//                .setParameter(3, post.getContents())
//                .setParameter(4, post.getImage())
//                .setParameter(5, post.getLikeCount())
//                .setParameter(6, post.getType())
//                .executeUpdate();
//    }


    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public void update(PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(Long.parseLong(postUpdateDto.getPostId())).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.update(postUpdateDto);
    }


    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Long updateLikeCount(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.updateLikeCount();
        return id;
    }

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Long minusLikeCount(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.minusLikeCount();
        return id;
    }
}
