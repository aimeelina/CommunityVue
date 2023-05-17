package com.aimeelina.communityvue.entity;

public class Page {
    //当前页码
    private int current=1;
    //每页最大条数
    private int limit=10;
    //总数据条数
    private int totalLine;
    //查询路径
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1){
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>=1&&limit<=100) {
            this.limit = limit;
        }
    }

    public int getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(int totalLine) {
        if(totalLine>=0){
            this.totalLine = totalLine;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    //获取当前页第一条数据的编号
    public int getOffset(){
        return (current-1)*limit;
    }
    //获取最大页数
    public int getMaxPage(){
        if(totalLine%limit==0){
            return totalLine/limit;
        }
        return totalLine/limit+1;
    }
    //下面两个函数是为了获得当前页前两页和后两页的页码
    public int getStart(){
        int start=current-2;
        return start>1?start:1;
    }
    public int getEnd(){
        int end=current+2;
        return end<getMaxPage()?end:getMaxPage();
    }
}
