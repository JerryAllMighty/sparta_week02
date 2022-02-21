package com.sparta.juteukki02.juteukki_week02.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostUpdateDto {
    @NotBlank(message = "내용을 입력해주세요.")
    private String contents;

    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String nickName;

    @NotBlank(message = "이미지 경로를 입력해주세요.")
    private String image;

    @NotBlank(message = "타입을 입력해주세요.")
    private String type;

    @NotNull(message = "좋아요를 입력해주세요.")
    private Integer likeCount;

    @NotBlank(message = "게시글 번호를 입력해주세요.")
    private String postId;

}
