package com.example.demo.utils;

public class ObjectMaker {

    public static org.json.simple.JSONObject getSimpleJSONObject() {
        return new org.json.simple.JSONObject();
    }

    public static org.json.simple.JSONArray getSimpleJSONArray() {
        return new org.json.simple.JSONArray();
    }

    public static org.json.JSONObject getJSONObject() {
        return new org.json.JSONObject();
    }

    public static org.json.JSONArray getJSONArray() {
        return new org.json.JSONArray();
    }

    //에러가 발생했을 때, 형식을 반환해주는 것
    public static org.json.simple.JSONObject getJSONObjectWithException(Exception exception) {
        org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
        jsonObject.put("result", false);
        jsonObject.put("message", exception.getMessage());
//        jsonObject.put("message", exception.toString());
        return jsonObject;
    }
}