package com.sparta.juteukki02.juteukki_week02.Dto;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserRegisterDto {
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String nickName;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String passwordCheck;
}
