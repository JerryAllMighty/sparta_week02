package com.sparta.juteukki02.juteukki_week02.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

@Getter
@Setter
public class UserDto {
    private String account;
    private String password;
    private String nickname;
}
