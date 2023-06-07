package com.aimeelina.communityvue.service;

import com.aimeelina.communityvue.entity.Exercise;
import com.aimeelina.communityvue.entity.ExerciseAnswer;
import com.aimeelina.communityvue.entity.Result;
import com.aimeelina.communityvue.mapper.CourseMapper;
import com.aimeelina.communityvue.mapper.ExerciseAnswerMapper;
import com.aimeelina.communityvue.mapper.ExerciseMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ExerciseMapper exerciseMapper;
    @Autowired
    private ExerciseAnswerMapper exerciseAnswerMapper;
    //查看习题
    public Result getExercises(int courseId, int chapterId ,int subChapterId){
        Exercise[] exercises = exerciseMapper.selectBySubChapterId(courseId, chapterId, subChapterId, 0, 20);
        return new Result(200,"获取习题",exercises);
    }
    //提交习题
    public Result uploadAns(int userId, ExerciseAnswer[] exerciseAnswers){
        for (int i=0;i<exerciseAnswers.length;i++){
            int exerciseId = exerciseAnswers[i].getExerciseId();
            //判断学生是否已经提交过该题的答案
            if(exerciseAnswerMapper.selectByUserIdAndExerciseId(userId,exerciseId)!=null){
                continue;
            }
            //查Exercise表对答案
            Exercise exercise = exerciseMapper.selectById(exerciseId);
            if(exercise.getAnswers()==exerciseAnswers[i].getAns()){
                //若答案正确，将Score设为1
                exerciseAnswers[i].setScore(1);
            }
            //更新ExerciseAnswer表
            exerciseAnswerMapper.insertAns(exerciseAnswers[i]);
        }
        //要不要返回批改结果？
        return new Result(200,"提交成功");
    }
    //查询已加入的课程
    public Result showCourses(int userId){
        return new Result(200,"查询已加入的课程");
    }
    //查询习题记录
    public Result showUserAns(int userId,int[] exerciseIds){

        ExerciseAnswer[] exerciseAnswers=new ExerciseAnswer[exerciseIds.length];
        for(int i=0;i<exerciseIds.length;i++){
            ExerciseAnswer exerciseAnswer = exerciseAnswerMapper.selectByUserIdAndExerciseId(userId, exerciseIds[i]);
            if(exerciseIds!=null){
                exerciseAnswers[i] = exerciseAnswer;
            }
        }
        return new Result(200,"查询习题记录成功",exerciseAnswers);
    }
    //查询自己学习情况
    // 申请加入课程
}
