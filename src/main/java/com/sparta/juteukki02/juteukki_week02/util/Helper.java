package com.sparta.juteukki02.juteukki_week02.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
public class Helper {
    public static String makeReturnJSON(String a, Object b, String c, Object d) {
        JSONObject obj = new JSONObject();
        obj.append(a, b);
        obj.append(c, d);
        return obj.toString();
    }
    public static String makeReturnJSON(String a, Object b, String c, Object d, String e, Object f, String g, Object h) {
        JSONObject obj = new JSONObject();
        obj.append(a, b);
        obj.append(c, d);
        obj.append(e, f);
        obj.append(g, h);
        return obj.toString();
    }


}
