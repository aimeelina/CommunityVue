package com.aimeelina.communityvue.entity;

public class SC {
    //Student-Course
    private int id;
    private int userId;
    private int CourseId;

    @Override
    public String toString() {
        return "SC{" +
                "id=" + id +
                ", userId=" + userId +
                ", CourseId=" + CourseId +
                '}';
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

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }
}
