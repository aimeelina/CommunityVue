package com.aimeelina.communityvue.service;

import com.aimeelina.communityvue.entity.Course;
import com.aimeelina.communityvue.entity.Exercise;
import com.aimeelina.communityvue.entity.Result;
import com.aimeelina.communityvue.mapper.CourseMapper;
import com.aimeelina.communityvue.mapper.ExerciseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ExerciseMapper exerciseMapper;

    //创建课程
    public Result createCourse(Course course){
        if(courseMapper.insertCourse(course)==1){
           return new Result(200,"创建课程成功");
        }
        return new Result(400,"创建课程失败");
    }
    //修改课程信息
    public Result updateCourse(Course course){
        if(courseMapper.updateAllById(course)==1){
            return new Result(200,"编辑课程信息成功");
        }
        return new Result(400,"编辑课程信息失败");
    }
    //导入知识图谱
    public Result updateKnowledgeGraphData(int courseId, String knowledgeGraphData){
        if(courseMapper.updateKnowledgeGraphDataById(courseId,knowledgeGraphData)==1){
            return new Result(200,"上传知识图谱成功");
        }
        return new Result(400,"上传知识图谱失败");
    }
    //导入习题与课程资料
    @Transactional(rollbackFor=Exception.class)
    public Result importExercises(Exercise[] exercises){
        for(int i=0;i<exercises.length;i++){
            exerciseMapper.insertExercise(exercises[i]);
        }
        return new Result(200,"上传习题成功");
        //不知道怎么在失败时返回信息？
    }
    //查询学生情况
    //批准加入课程


}
