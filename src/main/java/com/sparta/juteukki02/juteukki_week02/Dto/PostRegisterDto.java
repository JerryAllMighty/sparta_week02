package com.sparta.juteukki02.juteukki_week02.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostRegisterDto {
    private Integer likeCount;

    @NotBlank(message = "사용자 PK을 입력해주세요.")
    private String contents;

    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String nickName;

    @NotBlank(message = "이미지 경로를 입력해주세요.")
    private String image;

    @NotBlank(message = "타입을 입력해주세요.")
    private String type;
}
