package com.aimeelina.communityvue.mapper;

import com.aimeelina.communityvue.entity.Exercise;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExerciseMapper {
    int insertExercise(Exercise exercise);
    Exercise selectById(int id);
    Exercise[] selectBySubChapterId(int courseId, int chapterId ,int subChapterId,int offSet, int maxLine);
    int updateQuestionById(int id,String question);
    int updateOptionsById(int id,String options);
    int updateImgUrlById(int id,String imgUrl);
    int updateAnswersById(int id,int answers);
    int updatePdfUrlById(int id,String pdfUrl);
    int updateVideoUrlById(int id,String videoUrl);

    //查某一节中的题目数量
    int countQuestionBySubChapterId(int courseId, int chapterId ,int subChapterId);
    //要查某一章中有多少节有题目？


}
