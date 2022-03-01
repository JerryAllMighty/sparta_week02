package com.sparta.juteukki02.juteukki_week02.model;

import com.sparta.juteukki02.juteukki_week02.Dto.PostUpdateDto;
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

}
