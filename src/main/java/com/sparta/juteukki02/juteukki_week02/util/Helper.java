package com.sparta.juteukki02.juteukki_week02.util;

import com.sparta.juteukki02.juteukki_week02.model.MyLike;
import com.sparta.juteukki02.juteukki_week02.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
public class Helper {
    public static String makeReturnJSON(String a, Object b, String c, Object d) {
        JSONObject obj = new JSONObject();
        obj.put(a, b);
        obj.put(c, d);
        return obj.toString();
    }
    public static String makeReturnJSON(String a, Object b, String c, Object d, String e, Object f) {
        JSONObject obj = new JSONObject();
        obj.put(a, b);
        obj.put(c, d);
        obj.put(e, f);
        return obj.toString();
    }
    public static String makeReturnJSON(String a, Object b, String c, Object d, String e, Object f, String g, Object h) {
        JSONObject obj = new JSONObject();
        obj.put(a, b);
        obj.put(c, d);
        obj.put(e, f);
        obj.put(g, h);
        return obj.toString();
    }
    public static String makeReturnJSONList(String a, List<Post> b, String c, List<MyLike> d) {
        JSONObject obj = new JSONObject();
        obj.put(a, new JSONArray(b));
        obj.put(c, new JSONArray(d));
        return obj.toString();
    }


}
