package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import com.sparta.juteukki02.juteukki_week02.jwt.JwtTokenProvider;
import com.sparta.juteukki02.juteukki_week02.model.User;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import com.sparta.juteukki02.juteukki_week02.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String checkLogin(UserLoginDto userLoginDto){
        //  아직 로그인을 하지 않은 사용자라면, 아이디와 비밀번호로 정보 일치여부 탐색
        User member = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("아이디를 확인해주세요."));
        if (!passwordEncoder.matches(userLoginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        // 정상 로그인의 경우 JSON 전달
        String tokenString = jwtTokenProvider.createToken(member.getUsername(), member.getNickName(), member.getId());
        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "True");
        builder.addKeyValue("msg", "로그인에 성공하였습니다.");
        builder.addKeyValue("tokenname", tokenString);
        return builder.build().getReturnJSON();
    }

    public String checkRegister(UserRegisterDto userRegisterDto){
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();

        // 회원 ID 중복 확인
        Optional<User> foundId = userRepository.findByUsername(username);
        if (foundId.isPresent()) {
            Helper.JSONBuilder builder = new Helper.JSONBuilder();
            builder.addKeyValue("result", "fail");
            builder.addKeyValue("msg", "중복된 사용자 ID 가 존재합니다.");
            return builder.build().getReturnJSON();
        }
        // 회원 이름 중복 확인
        Optional<User> foundName = userRepository.findByNickName(nickName);
        if (foundName.isPresent()) {
            Helper.JSONBuilder builder = new Helper.JSONBuilder();
            builder.addKeyValue("result", "fail");
            builder.addKeyValue("msg", "중복된 사용자 닉네임이 존재합니다.");
            return builder.build().getReturnJSON();
        }

        // 빌더 패턴 적용, 회원 정보 저장
        User user = User.builder()
                .username(userRegisterDto.getUsername())
                // 패스워드 암호화
                .password(passwordEncoder.encode(password))
                .nickName(userRegisterDto.getNickName())
                    .build();
        userRepository.save(user);
        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "success");
        builder.addKeyValue("msg", "회원가입에 성공하였습니다.");
        return builder.build().getReturnJSON();
    }

    // 로그아웃시, 토큰을 무효한 토큰으로 만들어준다
    public String checkLogOut(HttpServletRequest request ){
        String header = jwtTokenProvider.resolveToken(request);
        jwtTokenProvider.invalidateToken(header);
        Helper.JSONBuilder builder = new Helper.JSONBuilder();
        builder.addKeyValue("result", "success");
        builder.addKeyValue("msg", "로그아웃에 성공하였습니다.");
        return builder.build().getReturnJSON();
    }
}
