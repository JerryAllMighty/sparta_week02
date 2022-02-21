package com.sparta.juteukki02.juteukki_week02.model;

import com.sparta.juteukki02.juteukki_week02.Dto.PostRegisterDto;
import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Post {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false)
    private String contents;

    @Column(columnDefinition = "integer default 0")
    private Integer likeCount;

    @Column(nullable = false, unique = false)
    private String nickName;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String type;

    public Post(PostRegisterDto postDto){
        this.nickName = postDto.getNickName();
        this.contents = postDto.getNickName();
        this.image = postDto.getNickName();
        this.type = postDto.getNickName();
        this.likeCount = 0;
    }

    public void updateLikeCount(){
        this.likeCount += 1;

    }
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

}
