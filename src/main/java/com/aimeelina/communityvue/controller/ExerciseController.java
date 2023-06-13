package com.aimeelina.communityvue.controller;

import com.aimeelina.communityvue.entity.ExerciseAnswer;
import com.aimeelina.communityvue.entity.ExerciseDisplay;
import com.aimeelina.communityvue.entity.Result;
import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.service.StudentService;
import com.aimeelina.communityvue.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class ExerciseController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping("/getExercise/{courseId}/{chapterId}/{subChapterId}")
    @ResponseBody
    public Result getExercises(@PathVariable("courseId")int courseId,
                               @PathVariable("chapterId")int chapterId,
                               @PathVariable("subChapterId")int subChapterId){
        User user = hostHolder.getUser();
        if (user != null) {
            return studentService.getExercises(courseId,chapterId,subChapterId,user.getId());
        }
        else {
            return new Result(400,"用户未登录，无法获取习题内容");
        }
    }

//    @RequestMapping(value = "/submitExercise",method = RequestMethod.POST)
//    @ResponseBody
//    public Result getExercises( @RequestBody List<ExerciseDisplay> exerciseDisplays){
//        User user = hostHolder.getUser();
//        if (user != null) {
////            return studentService.getExercises(courseId,chapterId,subChapterId,user.getId());
//            for (int i=0;i<exerciseDisplays.size();i++) System.out.println("ed"+i+":"+exerciseDisplays.get(i));
//            return new Result(222,"用户未登录，无法提交习题内容");
//        }
//        else {
//            return new Result(400,"用户未登录，无法提交习题内容");
//        }
//    }

    @RequestMapping(value = "/submitExercise",method = RequestMethod.POST)
    @ResponseBody
    public Result getExercises(@RequestBody List<ExerciseAnswer> exerciseAnswers){
        User user = hostHolder.getUser();
        if (user != null) {
            for (int i=0;i<exerciseAnswers.size();i++){
//                System.out.println("ed"+i+":"+exerciseAnswers.get(i));
                exerciseAnswers.get(i).setUserId(user.getId());
            }
            ExerciseAnswer[] exerciseAnswerArray=exerciseAnswers.toArray(new ExerciseAnswer[exerciseAnswers.size()]);
            return studentService.uploadAns(exerciseAnswerArray);
        }
        else {
            return new Result(400,"用户未登录，无法提交习题内容");
        }
    }
}
