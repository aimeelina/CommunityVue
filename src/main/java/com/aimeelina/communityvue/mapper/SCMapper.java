package com.aimeelina.communityvue.mapper;

import com.aimeelina.communityvue.entity.SC;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SCMapper {
    @Insert("insert INTO student_course_table (userId,courseId) VALUES (#{userId}, #{courseId})")
    int insertSC(int userId, int courseId);
    @Select("select courseId from student_course_table where userId=#{userId}")
    int[] selectByUserId(int userId);
    @Select("select userId from student_course_table where courseId=#{courseId}")
    int[] selectByCourseId(int courseId);

}
