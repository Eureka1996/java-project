package com.wufuqiang.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonArrayTest {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        for(int i = 0;i< 5;i++){
            JSONObject obj = new JSONObject();
            obj.put("name","wufq"+i);
            obj.put("age",i+20);
            jsonArray.add(obj);
        }

        System.out.println(jsonArray.toJSONString());

    }
}
