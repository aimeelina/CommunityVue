package com.aimeelina.communityvue.service;

import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.mapper.UserMapper;
import com.aimeelina.communityvue.utils.MailClient;
import com.aimeelina.communityvue.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${communityvue.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    public List<User> findAllUser(int offSet,int maxLine){
        return userMapper.selectAll(offSet,maxLine);
    }
    public int countUser(){
        return userMapper.getRowsNumber();
    }
    public Map<String,Object> register(User user){
        Map<String,Object> map=new HashMap<>();
        //检查是否有此用户名
        if(userMapper.selectByName(user.getUsername())!=null){
            map.put("usernameMsg","账号名已存在");
            //return map;
        }
        //检查是否有此邮箱
        if(userMapper.selectByEmail(user.getEmail())!=null){
            map.put("emailMsg","邮箱已被注册");
            //return map;
        }
        //加密用户密码
        user.setSalt(LoginUtils.generateUUID().substring(0,4));
        user.setPassword( LoginUtils.md5(user.getPassword()+user.getSalt()));
        user.setActivationCode(LoginUtils.generateUUID());
        user.setHeaderUrl(String.format ("http://images.nowcoder.com/head/&dt.png", new Random().nextInt(1000)));
        userMapper.insertUser(user);

        //发送激活邮件
        Context mailContext = new Context();
        mailContext.setVariable("email",user.getEmail());
        String url = domain+contextPath+"/activation/"+user.getId()+"/"+user.getActivationCode();
        mailContext.setVariable("url",url);
        String mailContent=templateEngine.process("MailActive",mailContext);
        mailClient.sendMail(user.getEmail(),"激活账号",mailContent);


        return map;
    }



}
