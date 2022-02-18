package com.sparta.juteukki02.juteukki_week02.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

@Getter
@Setter
public class UserDto {
    private String postid;
    private String writer;
    private String contents;
}
