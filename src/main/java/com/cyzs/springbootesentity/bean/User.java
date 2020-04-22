package com.cyzs.springbootesentity.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: xh
 * @create: 2020-04-19 18:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String name;

    private Integer age;

    private String email;

    private String address;


}
