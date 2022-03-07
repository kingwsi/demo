package com.demo.entity;

import lombok.Data;

/**
 * description:  user<br>
 * date: 2022/3/3 13:44 <br>
 * author: wangshu <br>
 */
@Data
public class User {
    private Integer id;
    private String username;
    private Integer age;
    private String gender;
    private String mobile;
}
