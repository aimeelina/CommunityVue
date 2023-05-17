package com.aimeelina.communityvue.controller;

import com.aimeelina.communityvue.entity.Result;
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
    @RequestMapping(path = "/register",method = RequestMethod.GET)
    public String getRegisterPage(){
        return "/";
    }
    @RequestMapping(path = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result valideteUser(@RequestParam("name") String userInfo){
        System.out.println("userInfo"+userInfo);
        return new Result(200);
    }
}
