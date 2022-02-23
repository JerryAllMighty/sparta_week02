package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.model.User;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

import static com.sparta.juteukki02.juteukki_week02.util.Helper.makeReturnJSON;


@RequiredArgsConstructor
@Service
public class UserService {
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String checkRegister(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String passwordcheck = user.getPasswordCheck();
        String nickName = user.getNickName();
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
//        password = passwordEncoder.encode(password);
        // DB에 실제로 넣을 데이터 형태로 만들어주고 넣어주기
        User user2 = new User(username, password, nickName);
        userRepository.save(user2);
        JSONObject result = new JSONObject(user2);
        return makeReturnJSON("result", "success", "msg", result);
    }
}
