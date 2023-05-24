package com.aimeelina.communityvue;

import com.aimeelina.communityvue.entity.LoginTicket;
import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.mapper.LoginTicketMapper;
import com.aimeelina.communityvue.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityVueApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Test
    public void selectByIdTest(){
        System.out.println(userMapper.selectById(1));
    }

    @Test
    public void selectByNameTest(){
        System.out.println(userMapper.selectByName("aimeelina"));
    }

    @Test
    public void insertUserTest(){
        User user = new User();
        user.setUsername("Test");
        user.setPassword("123456");
        user.setSalt("abcd");
        System.out.println(userMapper.insertUser(user));
    }
    @Test
    public void updateHeaderTest(){
        System.out.println(userMapper.updateHeader(4,"http://images.nowcoder.com/head/641t.png"));
    }
    @Test
    public void insertLoginTicketTest(){
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(1);
        loginTicket.setTicket("abcde");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }
    @Test
    public void updateLoginTicketTest(){
        System.out.println(loginTicketMapper.updateLoginTicket("abcde",1));
        System.out.println(loginTicketMapper.updateLoginTicket("abc",1));
    }
    @Test
    public void selectLoginTicketTest(){
        System.out.println(loginTicketMapper.selectLoginTicket("abcde"));
        System.out.println(loginTicketMapper.selectLoginTicket("abc"));
        System.out.println(loginTicketMapper.selectLoginTicket("01d90ddd4daf4d1ba82a8604a13ecfb0"));
    }

}