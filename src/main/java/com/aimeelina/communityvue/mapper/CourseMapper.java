package com.aimeelina.communityvue.mapper;

import com.aimeelina.communityvue.entity.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {
    int insertCourse(Course course);
    Course selectById(int id);
    Course[] selectByCreatorId(int creatorId, int offSet, int maxLine);
    int countByCreatorId(int creatorId);
    int updateIntroductionById(int id,String introduction);
    int updateStudentsNumById(int id,int studentsNum);
    int updateImgUrlById(int id,String imgUrl);
    int updateStatusById(int id,int status);
    int updateAllById(Course course);//能改courseName,creatorId,introduction,imgUrl ,status
    int updateKnowledgeGraphDataById(int id,String KnowledgeGraphDataById);

}
