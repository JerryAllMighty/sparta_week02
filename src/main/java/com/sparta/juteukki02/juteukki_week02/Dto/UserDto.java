package com.sparta.juteukki02.juteukki_week02.Dto;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserDto {
    private String account;

    private String password;

    private String nickname;
}
