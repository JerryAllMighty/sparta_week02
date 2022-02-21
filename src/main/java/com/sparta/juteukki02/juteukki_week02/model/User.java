package com.sparta.juteukki02.juteukki_week02.model;

import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {
    private String passwordCheck;
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    public User(String username, String password, String nickName) {
        this.username = username;
        this.password = password;
        this.nickName = nickName;
    }
    public User(UserLoginDto userLoginDto) {
        this.username = userLoginDto.getUsername();
        this.password = userLoginDto.getPassword();
    }

    public User(UserRegisterDto userRegisterDto) {
        this.username = userRegisterDto.getUsername();
        this.password = userRegisterDto.getPassword();
        this.nickName = userRegisterDto.getNickName();
        this.passwordCheck =userRegisterDto.getPasswordCheck();
    }
}