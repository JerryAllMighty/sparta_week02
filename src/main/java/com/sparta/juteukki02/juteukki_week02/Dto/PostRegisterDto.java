package com.sparta.juteukki02.juteukki_week02.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostRegisterDto {
    private Integer likeCount;
    
    private String contents;

    private String nickName;

    private String image;

    private String type;

    private Long userId;
}
