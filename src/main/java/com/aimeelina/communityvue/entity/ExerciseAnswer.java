package com.aimeelina.communityvue.entity;

public class ExerciseAnswer {
    /*
前端点击节点->根据前端传来的节点序号查找题目->将题目内容&题目id传给前端

点击提交答案->将题目id和答题内容传回来后端->根据题目id对比exercise表的正确答案,并将学生提交的答案存到ExerciseAnswer表
* */
    private int id;
    private int userId;
    private int exerciseId;
    private int ans;
    private int score;

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


