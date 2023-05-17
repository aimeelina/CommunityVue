package com.aimeelina.communityvue;

import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityVueApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByIdTest(){
        System.out.println(userMapper.selectById(1));
    }

    @Test
    public void selectByNameTest(){
        System.out.println(userMapper.selectByName("Jack"));
    }

    @Test
    public void insertUserTest(){
        User user = new User();
        user.setUsername("Test");
        user.setPassword("123456");
        user.setSalt("abcd");
        System.out.println(userMapper.insertUser(user));
    }

}