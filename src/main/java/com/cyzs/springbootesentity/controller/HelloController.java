package com.cyzs.springbootesentity.controller;

import com.cyzs.springbootesentity.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: xh
 * @create: 2020-04-19 18:02
 */
@RestController
public class HelloController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/getUser")
    public User getUser(){
        User user = new User();
        user.setName("王二");
        user.setAge(18);
        return user;
    }

    @GetMapping("/jdbctest")
    public User getUserFromJdbc(){
        List<User> query = jdbcTemplate.query("select * from t_user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String email = resultSet.getString(4);
                user.setId(id);
                user.setName(name);
                user.setAge(age);
                user.setEmail(email);
                return user;
            }
        });


        return query.get(0);
    }
}
