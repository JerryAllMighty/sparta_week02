package com.sparta.juteukki02.juteukki_week02.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostUpdateDto {
    private String contents;

    private String nickName;

    private String image;

    private String type;

    private Integer likeCount;

    private Long postId;

}
