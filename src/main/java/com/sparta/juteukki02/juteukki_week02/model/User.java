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

    public User(UserDto commentDto){
        this.account = commentDto.getPostid();
        this.password = commentDto.getWriter();
        this.nickname = commentDto.getContents();
    }
    public void update(CommentDto commentDto){
        this.postid = commentDto.getPostid();
        this.writer = commentDto.getWriter();
        this.contents = commentDto.getContents();

    }
}
