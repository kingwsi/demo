package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description:  <br>
 * date: 2022/3/3 13:41 <br>
 * author: wangshu <br>
 */
@SpringBootApplication
@MapperScan("com.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DemoApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}