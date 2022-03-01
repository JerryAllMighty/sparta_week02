package com.sparta.juteukki02.juteukki_week02.model;

import com.sparta.juteukki02.juteukki_week02.Dto.LikeDto;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.*;
import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@AllArgsConstructor
@Builder
public class MyLike {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    // 좋아요를 눌렀을 때, 필요한 값이 Request로 다 넘어왔는지 체크
    public String isValidLike(LikeDto likeDto){
        Long postId = likeDto.getPostId();
        Long userId = likeDto.getUserId();

        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "fail");

        // 게시물 ID 값 체크 : Null, 유효 여부
        if(postId == null || postId.equals(0L)){
            builder.addKeyValue("msg", "게시글 ID는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        // 사용자 ID 값 체크 : Null, 유효 여부
        else if(userId == null || userId.equals(0L)){
            builder.addKeyValue("msg", "사용자 ID는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        return "success";
    }

}
