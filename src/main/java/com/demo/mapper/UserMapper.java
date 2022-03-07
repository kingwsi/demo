package com.demo.mapper;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * description:  <br>
 * date: 2022/3/3 13:43 <br>
 * author: wangshu <br>
 */
@Mapper
public interface UserMapper {

    @Select("SELECT `id` FROM `user` WHERE `id` = #{id}")
    User getUserById(@Param("id") Integer id);

    @Insert("INSERT INTO USER (NAME, AGE, GENDER, MOBILE) VALUES (#{user.name}, #{user.age}, #{user.gender}, #{user.mobile})")
    void insertUser(@Param("user") User user);
}
