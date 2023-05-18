package com.aimeelina.communityvue.controller;

import com.aimeelina.communityvue.entity.Result;
import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;
//    @RequestMapping(path = "/register",method = RequestMethod.GET)
//    public String getRegisterPage(){
//        return "/";
//    }
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

}
