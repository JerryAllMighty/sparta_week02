package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.Dto.PostDeleteDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostRegisterDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostGetDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import com.sparta.juteukki02.juteukki_week02.jwt.JwtTokenProvider;
import com.sparta.juteukki02.juteukki_week02.model.*;
import com.sparta.juteukki02.juteukki_week02.service.PostService;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostRestController {
    private final LikeRepository likeRepository;

    private final PostService postService;
    private final PostRepository postRepository;

    private final JwtTokenProvider jwtTokenProvider;


    //게시글 조회 : 전체 게시물과, 사용자가 좋아요를 누른 게시물의 목록을 들고오기
    @PostMapping("/api/showpost")
    public String getPosts( @RequestBody PostGetDto postGetDto) {
        // 전체 게시글 조회
        List<Post> posts = postService.getTotal("total");
        // 사용자가 좋아요한 게시글 목록 조회
        List<MyLike> likes = postService.getMyLike(postGetDto.getUserId());
        // 위의 조회 리스트들을 담은 JSON 빌더 패턴으로 만들기
        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValueList("total", posts);
        builder.addKeyValueList("myLike", likes);
        return builder.build().getReturnJSON();

    }
    // 게시글 등록
    @PostMapping("/api/post")
    public String addPosts(@RequestBody PostRegisterDto postRegisterDto) {
        Post post = new Post();
        //넘겨받은 값이 nulL이거나, 기준 미달인지 체크
        String validationCheck = post.isValidRegister(postRegisterDto);
        if (validationCheck.equals("success")) {
            return postService.uploadPost(postRegisterDto);
        }else
            return validationCheck;
    }
    // 게시글 수정
    @PutMapping("/api/post")
    public String updatePosts(@RequestBody PostUpdateDto postUpdateDto) {
        Post post = new Post();
        //넘겨받은 값이 nulL이거나, 기준 미달인지 체크
        String validationCheck = post.isValidUpdate(postUpdateDto);
        if (validationCheck.equals("success")) {
            postService.update(postUpdateDto);
            return makeReturnJSON("result", "True", "msg", "게시글 수정 완료되었습니다.");
        }else
            return validationCheck;

    }
    // 게시글 삭제
    @Transactional
    @DeleteMapping("/api/post")
    public String deletePosts(@RequestBody PostDeleteDto postDeleteDto) {
        Post post = new Post();
        //넘겨받은 값이 nulL이거나, 기준 미달인지 체크
        String validationCheck = post.isValidDelete(postDeleteDto);
        if (validationCheck.equals("success")) {
            Long postId = postDeleteDto.getPostId();
            postRepository.deleteById(postId);
            // MyLike 테이블에서도 같이 삭제
            likeRepository.deleteByPostId(postId);
            return makeReturnJSON("result", "True", "msg","게시글 삭제 완료되었습니다.");
        }else
            return validationCheck;


    }
    // 프론트엔드와 약속한 방식으로 리턴해주기 위해 리턴 형태를 가공하는 함수
    public String makeReturnJSON(String title1, Object contents1, String title2, Object contents2 ){
        // 빌더 패턴 적용, 헬퍼 클래스 활용
        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue(title1, contents1);
        builder.addKeyValue(title2, contents2);
        return builder.build().getReturnJSON();
    }
    // 만약 필터에서 바로 걸러내지않는 방법을 선택한다면 (WebSecurityConfig : anyRequest.permitall()),
    // 컨트롤러에서 체크하고 메세지를 출력하게도 가능
    public String checkToken(HttpServletRequest request){
        String header = jwtTokenProvider.resolveToken(request);
        if (!jwtTokenProvider.validateToken(header))
        {
            return "fail";
        }
        return "success";
    }

    @PostMapping("/api/cicd")
    public String testCICD() {
        return "cicd";

    }
}
