package com.aimeelina.communityvue.entity;

public class Exercise {

    private int id;
    private int courseId;//Course表的主键
    private int chapterId;
    private int subChapterId;
    private int questionId;
    private String question;
    private String options;//选项间用@分割？
    private int answers;//6位二进制位,末位对应A或T，次末位对应B或F,依次类推
    private int type;//单选还是多选，0单选，1多选，2判断
    private String imgUrl;
    private String pdfUrl;
    private String videoUrl;

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", chapterId=" + chapterId +
                ", subChapterId=" + subChapterId +
                ", questionId=" + questionId +
                ", question='" + question + '\'' +
                ", options='" + options + '\'' +
                ", answers=" + answers +
                ", type=" + type +
                ", imgUrl='" + imgUrl + '\'' +
                ", pdfUrl='" + pdfUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getSubChapterId() {
        return subChapterId;
    }

    public void setSubChapterId(int subChapterId) {
        this.subChapterId = subChapterId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
