package com.sparta.juteukki02.juteukki_week02.util;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Collection;


@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
public class Helper {
    private String returnJSON;
// 원하는 JSON 형태로 만들기 위해 빌더 클래스 생성
    public static class JSONBuilder{
        JSONObject obj = new JSONObject();
        // 스트링 등 일반적인 형태를 JSON 형태에 담아주기
        public JSONBuilder addKeyValue(String key, Object value){
            this.obj.put(key, value);
            return this;
        }
        // 리스트 형태를 담아주기
        public JSONBuilder addKeyValueList(String key, Collection value){
            this.obj.put(key, new JSONArray(value));
            return this;
        }
        public Helper build(){
            return new Helper(obj);
        }
    }
    private Helper(JSONObject obj){
        returnJSON = obj.toString();
    }
    // 예상 JSON 형태
//    {
//        "title1":"contents1",
//         "title2":"contents2",
//    }

}
