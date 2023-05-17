package com.aimeelina.communityvue.controller;

import com.aimeelina.communityvue.entity.Page;
import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/users",method = RequestMethod.GET)
    public String showUsers(Model model, Page page){
        page.setTotalLine(userService.countUser());
        page.setPath("/users");
        page.setLimit(2);
        List<User> users = userService.findAllUser(page.getOffset(),page.getLimit());
        model.addAttribute("users",users);
        return "/showUsers";
    }

    @RequestMapping(path = "/usersJSON/{offset}/{maxLine}",method = RequestMethod.GET)
    @ResponseBody
    public List<User> showUsersJSON(@PathVariable("offset") int offset,@PathVariable("maxLine") int maxLine){
        return userService.findAllUser(offset,maxLine);
    }
}
