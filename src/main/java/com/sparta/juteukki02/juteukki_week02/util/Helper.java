package com.sparta.juteukki02.juteukki_week02.util;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
public class Helper {
    private String returnJSON;

    public static class JSONBuilder{
        JSONObject obj = new JSONObject();

        public JSONBuilder addKeyValue(String key, Object value){
            this.obj.put(key, value);
            return this;
        }
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

}
