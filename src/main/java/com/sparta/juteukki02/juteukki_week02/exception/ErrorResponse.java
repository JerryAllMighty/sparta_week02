package com.sparta.juteukki02.juteukki_week02.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private String code;
    private String description;
    private String detail;
    public ErrorResponse(String detail, String description){
        this.detail = detail;
        this.description = description;
    }
}
