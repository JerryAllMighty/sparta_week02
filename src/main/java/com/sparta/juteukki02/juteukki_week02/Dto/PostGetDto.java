package com.sparta.juteukki02.juteukki_week02.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostGetDto {
    @NotBlank(message = "사용자 ID를 입력해주세요.")
    private String username;

}
