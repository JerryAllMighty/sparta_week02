package com.sparta.juteukki02.juteukki_week02.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LikeDto {
    @NotBlank(message = "사용자 PK을 입력해주세요.")
    private String userId;

    @NotBlank(message = "게시글 PK를 입력해주세요.")
    private String postId;
}
