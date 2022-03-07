package com.demo;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description:  <br>
 * date: 2022/3/3 13:54 <br>
 * author: wangshu <br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DynamicDatasourceTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setGender("男");
        user.setUsername("Jis");
        user.setAge(18);
        user.setMobile("1151841515");
        userMapper.insertUser(user);
    }

    @Test
    public void testSelectUser() {
        User user = userMapper.getUserById(1);
        log.info("user = {}", user);
    }
}
