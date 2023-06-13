package com.aimeelina.communityvue.service;

import com.aimeelina.communityvue.entity.Exercise;
import com.aimeelina.communityvue.entity.ExerciseAnswer;
import com.aimeelina.communityvue.entity.ExerciseDisplay;
import com.aimeelina.communityvue.entity.Result;
import com.aimeelina.communityvue.mapper.CourseMapper;
import com.aimeelina.communityvue.mapper.ExerciseAnswerMapper;
import com.aimeelina.communityvue.mapper.ExerciseMapper;
import com.aimeelina.communityvue.utils.UtilFuncs;
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
    public Result getExercises(int courseId, int chapterId ,int subChapterId, int userID){
        Exercise[] exercises = exerciseMapper.selectBySubChapterId(courseId, chapterId, subChapterId, 0, 20);
        //将习题处理为前端要求的格式
        ExerciseDisplay[] eds= new ExerciseDisplay[exercises.length];
        for (int i=0;i<exercises.length;i++){
            Exercise exercise=exercises[i];
            ExerciseDisplay ed=new ExerciseDisplay();
            ed.setId(exercise.getId());
            ed.setQuestionId(exercise.getQuestionId());
            ed.setType(exercise.getType());
            ed.setQuestion(exercise.getQuestion());
            if(ed.getType()!=2){//单选或多选
                String[] options = exercise.getOptions().split("@");
                ed.setOptionA(options[0]);
                ed.setOptionB(options[1]);
                ed.setOptionC(options[2]);
                ed.setOptionD(options[3]);
            }
            //如果用户已提交过答案，则在返回数据中加上用户提交的答案和正确答案
            ExerciseAnswer exerciseAnswer=exerciseAnswerMapper.selectByUserIdAndExerciseId(userID,exercise.getId());
            if(exerciseAnswer!=null){
                ed.setCorrectAnswers(UtilFuncs.intAnswerToStringAnswer(exercise.getAnswers(),exercise.getType()));
                ed.setUserAnswers(UtilFuncs.intAnswerToStringAnswer(exerciseAnswer.getAns(),exercise.getType()));
                ed.setCorrect(ed.getCorrectAnswers().equals(ed.getUserAnswers()));
            }
            eds[i]=ed;
        }
        return new Result(200,"获取习题",eds);
    }


    //提交习题
    public Result uploadAns(ExerciseAnswer[] exerciseAnswers){
        for (int i=0;i<exerciseAnswers.length;i++){
            int exerciseId = exerciseAnswers[i].getExerciseId();
            //判断学生是否已经提交过该题的答案
            if(exerciseAnswerMapper.selectByUserIdAndExerciseId(exerciseAnswers[i].getUserId(),exerciseId)!=null){
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
