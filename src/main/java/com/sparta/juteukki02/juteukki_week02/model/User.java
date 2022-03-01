package com.sparta.juteukki02.juteukki_week02.model;

import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.*;
import javax.persistence.*;
import java.util.regex.Pattern;


@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@AllArgsConstructor
@Builder
public class User {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    //유효성 체크를 모델에서 하는 이유는, 이후에 단위 테스트에 용이하기 때문
    // 로그인 시에 넘겨받은 값이 유효한지 체크하는 함수
    public String isValidLogin(UserLoginDto userLoginDto){

        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        // 아이디 값 체크 : Null, 유효 여부
        if(username == null || username.isEmpty()){
            builder.addKeyValue("result", "fail");
            builder.addKeyValue("msg", "아이디는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        // 비밀번호 값 체크 : Null, 유효 여부
        else if(password == null || password.isEmpty()){
            builder.addKeyValue("result", "fail");
            builder.addKeyValue("msg", "비밀번호는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        return "success";
    }

    // 회원가입 시에 넘겨받은 값이 유효한지 체크하는 함수
    public String isValidRegister(UserRegisterDto userRegisterDto){
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String passwordCheck = userRegisterDto.getPasswordCheck();
        String nickName = userRegisterDto.getNickName();

        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "fail");
        // Null과 빈 문자열 체크
        if(username == null || username.isEmpty()){
            builder.addKeyValue("msg", "아이디는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        else if(password == null || password.isEmpty()){
            builder.addKeyValue("msg", "비밀번호는 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        else if(passwordCheck == null || passwordCheck.isEmpty()){
            builder.addKeyValue("msg", "비밀번호 확인은 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        else if(nickName == null || nickName.isEmpty()){
            builder.addKeyValue("msg", "별명은 필수 입력입니다.");
            return builder.build().getReturnJSON();
        }
        // 비즈니스 로직 적용
//        - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
        if(!Pattern.matches("^[A-Za-z0-9]*$", nickName) || nickName.length() < 3){
            builder.addKeyValue("msg", "닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)입니다.");
            return builder.build().getReturnJSON();
        }
//        - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
        if(password.contains(nickName) || password.length() < 4){
            builder.addKeyValue("msg", "비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함될 수 없습니다.");
            return builder.build().getReturnJSON();
        }
//        - 비밀번호 확인은 비밀번호와 정확하게 일치하기
        if(!password.equals(passwordCheck)){
            builder.addKeyValue("msg", "비밀번호 일치 여부를 확인해주세요.");
            return builder.build().getReturnJSON();
        }
        return "success";
    }

}