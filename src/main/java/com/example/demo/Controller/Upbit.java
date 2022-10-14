package com.example.demo.Controller;

import com.example.demo.Service.UpbitService;
import com.example.demo.utils.ObjectMaker;
import com.example.demo.utils.WriteTo;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class Upbit {
    private final UpbitService upbitService;

    @GetMapping("/upbit")
    public void test(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        upbitService.upbit();
        WriteTo.send(response,jsonObject);
    }
}
