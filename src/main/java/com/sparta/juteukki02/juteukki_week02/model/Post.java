package com.sparta.juteukki02.juteukki_week02.model;

import com.sparta.juteukki02.juteukki_week02.Dto.PostDeleteDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostRegisterDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.*;
import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Builder
@AllArgsConstructor
public class Post extends Timestamped{
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String contents;

    @Column(columnDefinition = "integer default 0")
    private Integer likeCount;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String type;

    public void updateLikeCount(){this.likeCount += 1;}
    public void minusLikeCount(){
        this.likeCount -= 1;
    }

    public void update(PostUpdateDto postUpdateDto){
        this.likeCount = postUpdateDto.getLikeCount();
        this.image = postUpdateDto.getImage();
        this.contents = postUpdateDto.getContents();
        this.nickName = postUpdateDto.getNickName();
        this.type = postUpdateDto.getType();

    }

    //유효성 체크를 모델에서 하는 이유는, 단위 테스트를 쉽게하기 위함.
    //게시글 등록 유효성 체크
    public String isValidRegister(PostRegisterDto postRegisterDto){
        String contents = postRegisterDto.getContents();
        String image = postRegisterDto.getImage();
        String type = postRegisterDto.getType();
        String nickName = postRegisterDto.getNickName();
        Long userId = postRegisterDto.getUserId();

        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "fail");

        // 내용 값 체크 : Null, 유효 여부
        if(contents == null || contents.isEmpty()){
            builder.addKeyValue("msg", "내용은 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        // 이미지 값 체크 : Null, 유효 여부
        else if(image == null || image.isEmpty()){
            builder.addKeyValue("msg", "이미지는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        else if(type == null || type.isEmpty()){
            builder.addKeyValue("msg", "타입은 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        else if(nickName == null || nickName.isEmpty()){
            builder.addKeyValue("msg", "닉네임은 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        else if(userId == null || userId.equals(0L)){
            builder.addKeyValue("msg", "사용자 ID는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        return "success";
    }

    //게시글 수정 유효성 체크
    public String isValidUpdate(PostUpdateDto postUpdateDto){
        String contents = postUpdateDto.getContents();
        String image = postUpdateDto.getImage();
        String type = postUpdateDto.getType();
        String nickName = postUpdateDto.getNickName();
        Long postId = postUpdateDto.getPostId();
        Integer likeCount = postUpdateDto.getLikeCount();

        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "fail");

        // 내용 값 체크 : Null, 유효 여부
        if(contents == null || contents.isEmpty()){
            builder.addKeyValue("msg", "내용은 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        // 이미지 값 체크 : Null, 유효 여부
        else if(image == null || image.isEmpty()){
            builder.addKeyValue("msg", "이미지는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        // 타입 값 체크 : Null, 유효 여부
        else if(type == null || type.isEmpty()){
            builder.addKeyValue("msg", "타입은 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        // 닉네임 값 체크 : Null, 유효 여부
        else if(nickName == null || nickName.isEmpty()){
            builder.addKeyValue("msg", "닉네임은 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        // 사용자 ID 값 체크 : Null, 유효 여부
        else if(postId == null || postId.equals(0L)){
            builder.addKeyValue("msg", "사용자 ID는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        // 좋아요 개수 값 체크 : Null, 유효 여부
        else if(likeCount == null || likeCount.equals(0)){
            builder.addKeyValue("msg", "좋아요 개수는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        return "success";
    }

    //게시글 삭제
    public String isValidDelete(PostDeleteDto postDeleteDto){
        Long postId = postDeleteDto.getPostId();

        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "fail");

        // 게시글 ID 값 체크 : Null, 유효 여부
        if(postId == null || postId.equals(0L)){
            builder.addKeyValue("msg", "게시글 ID는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        return "success";
    }

}
