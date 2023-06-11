package com.aimeelina.communityvue.entity;

public class ExerciseAnswer {
/*
存储用户提交的答案
用户点击提交->将题目id和作答内容传回来后端->根据题目id对比exercise表的正确答案,并将学生提交的答案存到ExerciseAnswer表
*/
    private int id;
    private int userId;
    private int exerciseId;
    private int ans;
    private int score;//1为正确0为错误

    @Override
    public String toString() {
        return "ExerciseAnswer{" +
                "id=" + id +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", ans=" + ans +
                ", score=" + score +
                '}';
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }
}


