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

    public User(UserDto userDto){
        this.account = userDto.getAccount();
        this.password = userDto.getPassword();
        this.nickname = userDto.getNickname();
    }
    public void update(UserDto userDto){
        this.account = userDto.getAccount();
        this.password = userDto.getPassword();
        this.nickname = userDto.getNickname();

    }
}
