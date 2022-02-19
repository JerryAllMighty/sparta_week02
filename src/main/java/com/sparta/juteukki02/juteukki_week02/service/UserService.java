package com.sparta.juteukki02.juteukki_week02.service;

import com.sparta.juteukki02.juteukki_week02.model.User;
import com.sparta.juteukki02.juteukki_week02.model.UserDto;
import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.regex.Pattern;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Long update(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        user.update(userDto);
        return id;
    }

    public String checkLogin(String id, String pw){
        JSONObject obj = new JSONObject();
        if(userRepository.existsIdPw(id, pw)){
            obj.append("result", "success");
            obj.append("msg", "로그인에 성공했습니다.");
            return obj.toString();
        }
        else{
            obj.append("result", "fail");
            obj.append("msg", "닉네임 또는 패스워드를 확인해주세요.");
            return obj.toString();
        }

    }

    public String checkRegister(User user){
        String nickname = user.getNickname();
        String password = user.getPassword();
        String passwordcheck = user.getPasswordcheck();
//        - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
        if(!Pattern.matches("^[A-Za-z0-9]*$", nickname) || nickname.length() < 3){
            JSONObject obj = new JSONObject();
            obj.append("result", "fail");
            obj.append("msg", "닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)입니다.");
            return obj.toString();
        }
//        - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
        if(password.contains(nickname) || password.length() < 4){
            JSONObject obj = new JSONObject();
            obj.append("result", "fail");
            obj.append("msg", "비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함될 수 없습니다.");
            return obj.toString();
        }
//        - 비밀번호 확인은 비밀번호와 정확하게 일치하기
        if(!password.equals(passwordcheck)){
            JSONObject obj = new JSONObject();
            obj.append("result", "fail");
            obj.append("msg", "비밀번호 일치 여부를 확인해주세요.");
            return obj.toString();
        }
        userRepository.save(user);
        JSONObject obj = new JSONObject();
        JSONObject result = new JSONObject(user);
        obj.append("result", "success");
        obj.append("msg", result);
        return obj.toString();
    }
}
