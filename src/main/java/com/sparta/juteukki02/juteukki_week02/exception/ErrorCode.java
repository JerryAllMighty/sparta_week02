package com.sparta.juteukki02.juteukki_week02.exception;

import lombok.Getter;


public enum ErrorCode {
    NOT_NULL("Error","필수 값이 누락되었습니다."),
    MIN_VALUE("Error","필수 값이 누락되었습니다.");

    @Getter
    private String code;

    @Getter
    private String description;

    ErrorCode(String code, String description){
        this.code = code;
        this.description = description;
    }
}
