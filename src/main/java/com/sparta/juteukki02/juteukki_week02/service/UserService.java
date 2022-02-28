package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.Dto.UserLoginDto;
import com.sparta.juteukki02.juteukki_week02.Dto.UserRegisterDto;
import com.sparta.juteukki02.juteukki_week02.jwt.JwtTokenProvider;
import com.sparta.juteukki02.juteukki_week02.model.User;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.sparta.juteukki02.juteukki_week02.util.Helper.makeReturnJSON;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String checkLogin(UserLoginDto userLoginDto, HttpServletRequest request){
        //        현재 사용자가 로그인을 했는지 체크
        String header = jwtTokenProvider.resolveToken(request);
        if (jwtTokenProvider.validateToken(header))
        {
            return "이미 로그인한 사용자입니다.";
        }
//        아직 로그인을 하지 않은 사용자라면, 아이디와 비밀번호로 정보 일치여부 탐색
        User member = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("아이디를 확인해주세요."));
        if (!passwordEncoder.matches(userLoginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        // 정상 로그인의 경우 JSON 전달
        String tokenString = jwtTokenProvider.createToken(member.getUsername(), member.getNickName(), member.getId());
        return makeReturnJSON("result", "True", "msg", "로그인에 성공하였습니다.", "tokenname",tokenString);
    }

    public String checkRegister(UserRegisterDto user, HttpServletRequest request){
        //        현재 사용자가 로그인을 했는지 체크
        String header = jwtTokenProvider.resolveToken(request);
        if (jwtTokenProvider.validateToken(header))
        {
            return "이미 로그인한 사용자입니다.";
        }
        String username = user.getUsername();
        String password = user.getPassword();
        String nickName = user.getNickName();
// 회원 ID 중복 확인
        Optional<User> foundId = userRepository.findByUsername(username);
        if (foundId.isPresent()) {
            return makeReturnJSON("result", "fail", "msg", "중복된 사용자 ID 가 존재합니다.");
        }
        // 회원 이름 중복 확인
        Optional<User> foundName = userRepository.findByNickName(nickName);
        if (foundName.isPresent()) {
            return makeReturnJSON("result", "fail", "msg", "중복된 사용자 닉네임이 존재합니다.");
        }

// 패스워드 암호화
        password = passwordEncoder.encode(password);
        // 객체를 만들어 넣어주기
        User user2 = new User(username, password, nickName);
        userRepository.save(user2);
        return makeReturnJSON("result", "success", "msg", "회원가입에 성공하였습니다.");
    }
    public String checkLogOut(HttpServletRequest request ){
        String header = jwtTokenProvider.resolveToken(request);
        jwtTokenProvider.invalidateToken(header);
        return makeReturnJSON("result", "success", "msg", "로그아웃에 성공하였습니다.");
    }
}
