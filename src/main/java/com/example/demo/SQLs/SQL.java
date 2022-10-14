package com.example.demo.SQLs;

public class SQL {
    public static final String FindVictim = "SELECT ifnull(count(*),0) FROM victims WHERE macAddress = ?";
    public static final String injection = "INSERT INTO victims VALUES(default, ?, default,default,default)";
    public static final String encrpts = "UPDATE victims SET code = ? WHERE macAddress = ? ";
    public static final String deposit = "Select code from victims WHERE macAddress = ? ";
    public static final String email = "UPDATE victims SET email = ? WHERE macAddress = ? ";
    public static final String sendEmail = "Select email from victims";
}

