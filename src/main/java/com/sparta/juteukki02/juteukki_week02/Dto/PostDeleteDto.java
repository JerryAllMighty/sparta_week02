package com.sparta.juteukki02.juteukki_week02.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostDeleteDto {
    @NotBlank(message = "게시글 번호를 입력해주세요.")
    private String postId;
}
