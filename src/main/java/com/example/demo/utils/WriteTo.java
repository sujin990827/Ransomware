package com.example.demo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//응답형식 지정
public class WriteTo {
    public static void send(HttpServletResponse response, org.json.simple.JSONObject jsonObject) {
        PrintWriter writer = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            writer = response.getWriter();
            writer.println(jsonObject.toJSONString() );
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
