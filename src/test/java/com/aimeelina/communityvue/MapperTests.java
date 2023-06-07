package com.aimeelina.communityvue;

import com.aimeelina.communityvue.entity.*;
import com.aimeelina.communityvue.mapper.*;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityVueApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ExerciseMapper exerciseMapper;
    @Autowired
    private SCMapper scMapper;

    /*
    * 测试UserMapper
    */
    @Test
    public void selectByIdTest(){
        System.out.println(userMapper.selectById(1));
    }
    @Test
    public void selectByNameTest(){
        System.out.println(userMapper.selectByName("aimeelina"));
    }
    @Test
    public void insertUserTest(){
        User user = new User();
        user.setUsername("Test");
        user.setPassword("123456");
        user.setSalt("abcd");
        System.out.println(userMapper.insertUser(user));
    }
    @Test
    public void updateHeaderTest(){
        System.out.println(userMapper.updateHeader(4,"http://images.nowcoder.com/head/641t.png"));
    }

    /*
     * 测试LoginTicketMapper
     */
    @Test
    public void insertLoginTicketTest(){
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(1);
        loginTicket.setTicket("abcde");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }
    @Test
    public void updateLoginTicketTest(){
        System.out.println(loginTicketMapper.updateLoginTicket("abcde",1));
        System.out.println(loginTicketMapper.updateLoginTicket("abc",1));
    }
    @Test
    public void selectLoginTicketTest(){
        System.out.println(loginTicketMapper.selectLoginTicket("abcde"));
        System.out.println(loginTicketMapper.selectLoginTicket("abc"));
        System.out.println(loginTicketMapper.selectLoginTicket("01d90ddd4daf4d1ba82a8604a13ecfb0"));
    }

    /*
     * 测试CourseMapper
     */
    @Test
    public void insertCourseTest(){
        Course course=new Course();
        course.setCourseName("Math");
        course.setCreatorId(3);
        course.setIntroduction("test course");
        courseMapper.insertCourse(course);
    }
    @Test
    public void CourseSelectByCreatorIdTest(){
        Course[] coursesList=courseMapper.selectByCreatorId(3,0,5);
        for (int i=0;i<coursesList.length;i++){
            System.out.println(coursesList[i]);
        }
    }
    @Test
    public void CourseSelectByIdTest(){
        Course courses=courseMapper.selectById(1);
        System.out.println(courses);
    }
    @Test
    public void CourseCountByCreatorIdTest(){
        System.out.println(courseMapper.countByCreatorId(3));
        System.out.println(courseMapper.countByCreatorId(2));
    }
    @Test
    public void CourseUpdateIntroductionByIdTest(){
        courseMapper.updateIntroductionById(1,"updated intro");
        Course courses=courseMapper.selectById(1);
        System.out.println(courses);
    }
    @Test
    public void CourseUpdateStudentsNumByIdTest(){
        courseMapper.updateStudentsNumById(1,3);
    }
    @Test
    public void CourseUpdateAllByIdTest(){
        Course course=courseMapper.selectById(1);
        course.setIntroduction("test UpdateAllById");
        courseMapper.updateAllById(course);
    }


    /*
     * 测试ExerciseMapper
     */
    @Test
    public void insertExerciseTest(){
        Exercise exercise=new Exercise();
        exercise.setChapterId(1);
        exercise.setSubChapterId(1);
        exercise.setQuestionId(1);
        exercise.setQuestion("请问以下哪项正确");
        exercise.setOptions("@不是这项@是这项@看别的@错的选项");
        exercise.setAnswers(2);
        exercise.setType(0);
        exercise.setCourseId(1);
        exerciseMapper.insertExercise(exercise);
        exercise=new Exercise();
        exercise.setChapterId(1);
        exercise.setSubChapterId(1);
        exercise.setQuestionId(2);
        exercise.setQuestion("请问以下哪项错误");
        exercise.setOptions("@不是这项@看别的@是这项@错的选项");
        exercise.setAnswers(4);
        exercise.setType(0);
        exercise.setCourseId(1);
        exerciseMapper.insertExercise(exercise);
    }
    @Test
    public void ExerciseSelectBySubChapterIdTest(){
        Exercise[] eList=exerciseMapper.selectBySubChapterId(1,1,1,0,5);
        for (int i=0;i<eList.length;i++){
            System.out.println(eList[i]);
        }
    }
    @Test
    public void ExerciseCountQuestionBySubChapterIdTest(){
        System.out.println(exerciseMapper.countQuestionBySubChapterId(1,1,1));

    }

    /*
     * 测试SCMapper
     */
    @Test
    public void insertSCTest(){
        System.out.println(scMapper.insertSC(2,1));
        System.out.println(scMapper.insertSC(2,2));
    }


    }