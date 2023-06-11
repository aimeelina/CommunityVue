package com.aimeelina.communityvue.controller;

import com.aimeelina.communityvue.annotation.LoginRequired;
import com.aimeelina.communityvue.entity.Result;
import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.service.StudentService;
import com.aimeelina.communityvue.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExerciseController {
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private StudentService studentService;
    @LoginRequired
    @RequestMapping(value = "/getExercise/{courseId}/{chapterId}/{subChapterId}",method = RequestMethod.GET)
    @ResponseBody
    public Result getExercises(@PathVariable("courseId") int courseId,
                               @PathVariable("chapterId") int chapterId,
                               @PathVariable("subChapterId") int subChapterId){
        //后续可能要加一个判断用户有没有购买该课程的功能
        User user = hostHolder.getUser();
        return studentService.getExercises(courseId,chapterId,subChapterId,user.getId());
    }
}
