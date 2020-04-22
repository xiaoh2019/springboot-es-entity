package com.cyzs.springbootesentity.controller;

import com.cyzs.springbootesentity.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: xh
 * @create: 2020-04-19 19:00
 */
@RestController("/user")
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/findUserById")
    public User findUserById(Integer id){
        String sql = String.format("select * from t_user where id = %d", id);
        User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String email = resultSet.getString(4);
                String address = resultSet.getString(5);
                user.setId(id);
                user.setName(name);
                user.setAge(age);
                user.setEmail(email);
                user.setAddress(address);
                return user;
            }
        });
        return user;
    }

    public String addUserTest(){
        String sql = "";
        jdbcTemplate.execute(sql);
        return "success";
    }
}
