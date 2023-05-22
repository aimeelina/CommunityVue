package com.aimeelina.communityvue.service;

import com.aimeelina.communityvue.entity.LoginTicket;
import com.aimeelina.communityvue.entity.Result;
import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.mapper.LoginTicketMapper;
import com.aimeelina.communityvue.mapper.UserMapper;
import com.aimeelina.communityvue.utils.MailClient;
import com.aimeelina.communityvue.utils.LoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${communityvue.path.front-domain}")
    private String frontDomain;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    public List<User> findAllUser(int offSet,int maxLine){
        return userMapper.selectAll(offSet,maxLine);
    }
    public int countUser(){
        return userMapper.getRowsNumber();
    }
    public Result register(User user){
        //Map<String,Object> map=new HashMap<>();
        Result result=new Result(200,"注册成功，请到邮箱点击激活链接");
        //检查是否有此用户名
        if(userMapper.selectByName(user.getUsername())!=null){
            result.setCode(400);
            result.setMessage("账号名已存在");
            return result;
        }
        //检查是否有此邮箱
        if(userMapper.selectByEmail(user.getEmail())!=null){
            result.setCode(400);
            result.setMessage("邮箱已被注册");
            //return map;
        }
        //加密用户密码
        user.setSalt(LoginUtils.generateUUID().substring(0,4));
        user.setPassword( LoginUtils.md5(user.getPassword()+user.getSalt()));
        user.setActivationCode(LoginUtils.generateUUID());
        user.setHeaderUrl(String.format ("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        userMapper.insertUser(user);

        //发送激活邮件
//        Context mailContext = new Context();
//        mailContext.setVariable("email",user.getEmail());
        String url = frontDomain+contextPath+"#/activation/"+user.getId()+"/"+user.getActivationCode();
//        mailContext.setVariable("url",url);
//        String mailContent=templateEngine.process("MailActive",mailContext);
//        mailClient.sendMail(user.getEmail(),"激活账号",mailContent);
        System.out.println("激活网址："+url);
        return result;
    }
    public Result activation(int userID, String activationCode){
        Result result=new Result(200,"激活成功");
        User user=userMapper.selectById(userID);
        if(user==null){
            result.setCode(400);
            result.setMessage("用户不存在");
            return result;
        }
        else if(user.getStatus()==1){
            result.setCode(400);
            result.setMessage("请勿重复激活");
            return result;
        }
        else if(StringUtils.isBlank(activationCode)||!activationCode.equals(user.getActivationCode())){
            result.setCode(400);
            result.setMessage("激活码错误");
            return result;
        }
        userMapper.updateStatus(userID,1);
        return result;
    }

    public Result login(String username, String password, int expiredSeconds){
        Result result=new Result(200,"登录成功");
        if(StringUtils.isBlank(username)){
            result.setCode(400);
            result.setMessage("请输入用户名");
            return result;
        }
        if(StringUtils.isBlank(password)){
            result.setCode(400);
            result.setMessage("请输入密码");
            return result;
        }
        System.out.println("username is"+username);
        User user=userMapper.selectByName(username);
        if(user==null){
            result.setCode(400);
            result.setMessage("用户不存在");
            return result;
        }
        if(user.getStatus()==0){
            result.setCode(400);
            result.setMessage("账号未激活");
            return result;
        }
        if(!user.getPassword().equals(LoginUtils.md5(password+user.getSalt()))){
            result.setCode(400);
            result.setMessage("密码错误");
            return result;
        }
        //登录成功的处理
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(LoginUtils.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*expiredSeconds));
        loginTicketMapper.insertLoginTicket(loginTicket);
        result.setData(loginTicket.getTicket());
        return result;
    }

    public Result logout(String ticket){
        System.out.println("logout ticket: "+ticket);
        if(StringUtils.isBlank(ticket))return new Result(400,"用户无ticket");
        loginTicketMapper.updateLoginTicket(ticket,1);
        return new Result(200,"退出登录");
    }

}
