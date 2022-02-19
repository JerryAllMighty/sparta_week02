package com.sparta.juteukki02.juteukki_week02.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String account;

    //    @Transient
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

//    비밀번호 확인을 위해서 만듬.
    private String passwordcheck;

    public User(UserDto userDto){
        this.account = userDto.getAccount();
        this.password = userDto.getPassword();
        this.nickname = userDto.getNickname();
    }
    public User(UserLoginDto userLoginDto){
        this.account = userLoginDto.getAccount();
        this.password = userLoginDto.getPassword();
    }
    public User(UserRegisterDto userRegisterDto){
        this.account = userRegisterDto.getAccount();
        this.password = userRegisterDto.getPassword();
        this.nickname = userRegisterDto.getNickname();
        this.passwordcheck = userRegisterDto.getPasswordcheck();

    }
    public void update(UserDto userDto){
        this.account = userDto.getAccount();
        this.password = userDto.getPassword();
        this.nickname = userDto.getNickname();

    }

}
