package com.sparta.juteukki02.juteukki_week02.model;

import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.sparta.juteukki02.juteukki_week02.util.Helper.makeReturnJSON;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@AllArgsConstructor
@Builder
public class User implements UserDetails {
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
    public String isValidLogin(UserLoginDto userLoginDto){
        if(userLoginDto.getUsername().equals(null)){
            return makeReturnJSON("result", "fail", "msg", "아이디는 필수 입력입니다.");
        }
        if(userLoginDto.getPassword().equals(null)){
            return makeReturnJSON("result", "fail", "msg", "비밀번호는 필수 입력입니다.");
        }
        return "success";
    }

    public User(UserRegisterDto userRegisterDto) {
        this.username = userRegisterDto.getUsername();
        this.password = userRegisterDto.getPassword();
        this.nickName = userRegisterDto.getNickName();
        this.passwordCheck =userRegisterDto.getPasswordCheck();
    }
    public String isValidRegister(UserRegisterDto userRegisterDto){
        if(userRegisterDto.getUsername().equals(null)){
            return makeReturnJSON("result", "fail", "msg", "아이디는 필수 입력입니다.");
        }
        if(userRegisterDto.getPassword().equals(null)){
            return makeReturnJSON("result", "fail", "msg", "비밀번호는 필수 입력입니다.");
        }
        if(userRegisterDto.getPasswordCheck().equals(null)){
            return makeReturnJSON("result", "fail", "msg", "비밀번호 확인은 필수 입력입니다.");
        }
        if(userRegisterDto.getNickName().equals(null)){
            return makeReturnJSON("result", "fail", "msg", "별명은 필수 입력입니다.");
        }
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String passwordcheck = userRegisterDto.getPasswordCheck();
        String nickName = userRegisterDto.getNickName();
//        - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
        if(!Pattern.matches("^[A-Za-z0-9]*$", nickName) || nickName.length() < 3){
            return makeReturnJSON("result", "fail", "msg", "닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)입니다.");
        }
//        - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
        if(password.contains(nickName) || password.length() < 4){
            return makeReturnJSON("result", "fail", "msg", "비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함될 수 없습니다.");
        }
//        - 비밀번호 확인은 비밀번호와 정확하게 일치하기
        if(!password.equals(passwordcheck)){
            return makeReturnJSON("result", "fail", "msg", "비밀번호 일치 여부를 확인해주세요.");
        }
        return "success";
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}