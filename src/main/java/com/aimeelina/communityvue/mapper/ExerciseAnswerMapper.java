package com.aimeelina.communityvue.mapper;

import com.aimeelina.communityvue.entity.ExerciseAnswer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExerciseAnswerMapper {
    int insertAns(ExerciseAnswer exerciseAnswer);
    ExerciseAnswer selectByUserIdAndExerciseId(int userId, int exerciseId);
}
