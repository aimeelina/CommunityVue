package com.aimeelina.communityvue.controller;

import com.aimeelina.communityvue.entity.Result;
import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.service.UserService;
import com.aimeelina.communityvue.utils.ImageToBase64Util;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static java.util.Base64.getEncoder;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private Producer kaptchaProducer;

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        System.out.println("userInfo:"+user);
        return userService.register(user);
    }

    @RequestMapping(path = "/activation/{userID}/{code}",method = RequestMethod.GET)
    @ResponseBody
    public Result activate(@PathVariable("userID")int userID,@PathVariable("code")String activationCode){
        return userService.activation(userID,activationCode);
    }

    @RequestMapping(path = "/login/getKaptcha",method = RequestMethod.GET)
    @ResponseBody
    public Result getKaptcha(HttpSession session){
        String text = kaptchaProducer.createText();
        BufferedImage image=kaptchaProducer.createImage(text);
        //将验证码的正确答案存入session
        session.setAttribute("kaptcha",text);
        System.out.println("session (getKaptcha):"+session);
        //将图片传回给前端
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            // 设置图片的格式
            ImageIO.write(image, "png", stream);
        } catch (IOException e) {
            System.out.println("生成验证码响应失败"+e.getMessage());
        }
        byte[] bytes = Base64.encodeBase64(stream.toByteArray());
        String base64 = new String(bytes);
        return new Result(200,"获取验证码成功",base64);
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("code") String code,
                        HttpSession session){
        System.out.println("session:"+session);
        String codeGt = (String) session.getAttribute("kaptcha");
        if(StringUtils.isBlank(code)||!code.equalsIgnoreCase(codeGt)){
            System.out.println("codeGt:"+codeGt);
            System.out.println("code:"+code);
            return new Result(400,"验证码错误");
        }
        return userService.login(username,password,3600*12);
    }

}
