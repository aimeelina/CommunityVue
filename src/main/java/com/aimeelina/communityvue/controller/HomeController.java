package com.aimeelina.communityvue.controller;

import com.aimeelina.communityvue.annotation.LoginRequired;
import com.aimeelina.communityvue.entity.Page;
import com.aimeelina.communityvue.entity.Result;
import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.service.UserService;
import com.aimeelina.communityvue.utils.HostHolder;
import com.aimeelina.communityvue.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@CrossOrigin
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;
    @Value("${communityvue.path.upload}")
    private String uploadPath;
    @Value("${communityvue.path.back-domain}")
    private String backDomain;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String showUsers(Model model, Page page) {
        page.setTotalLine(userService.countUser());
        page.setPath("/users");
        page.setLimit(2);
        List<User> users = userService.findAllUser(page.getOffset(), page.getLimit());
        model.addAttribute("users", users);
        return "/showUsers";
    }

    @RequestMapping(path = "/userInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result UserInfo() {
        User user = hostHolder.getUser();
        if (user != null) {
            return new Result(200, "获取用户信息成功", user);
        }
        return new Result(400, "获取用户信息失败");
    }


    @RequestMapping(path = "/usersJSON/{offset}/{maxLine}", method = RequestMethod.GET)
    @ResponseBody
    public List<User> showUsersJSON(@PathVariable("offset") int offset, @PathVariable("maxLine") int maxLine) {
        return userService.findAllUser(offset, maxLine);
    }

    @LoginRequired
    @RequestMapping(path = "/uploadHeader", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadHeader(@RequestParam("file") MultipartFile headerImg) {
        if (headerImg == null) {
            return new Result(400, "缺少图片");
        }
        String fileName = headerImg.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        fileName = LoginUtils.generateUUID() + fileType;
        File file = new File(uploadPath + "/" + fileName);
        try {
            headerImg.transferTo(file);
        } catch (IOException e) {
            System.out.println("头像写入失败：" + e.getMessage());
            throw new RuntimeException(e);
        }
        //注意！存入user的需要是外部访问路径而不是本地路径
        String savePath = backDomain + contextPath + "/assets/header/" + fileName;
//        System.out.println("hostHolder.getUser():"+hostHolder.getUser());
        User user = hostHolder.getUser();
//        System.out.println("savePath："+savePath);
//        System.out.println("savePath.length():"+savePath.length());
        if (userService.updateHeader(user.getId(), savePath) == 1) {
            return new Result(200, "上传头像成功");
        }
        return new Result(400, "上传头像失败");
    }

    @RequestMapping(path = "/assets/header/{fileName}", method = RequestMethod.GET)
    public void getHeaderImg(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        fileName = uploadPath + "/" + fileName;
        String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
        response.setContentType("image/"+suffix);
        try (
                OutputStream os=response.getOutputStream();
                FileInputStream fis =new FileInputStream(fileName);
        ){
            byte[] buffer = new byte[1024];
            int b=0;
            while((b= fis.read(buffer))!=-1){
                os.write(buffer,0,b);
            }
        } catch (IOException e) {
            System.out.println("读取头像失败"+e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
