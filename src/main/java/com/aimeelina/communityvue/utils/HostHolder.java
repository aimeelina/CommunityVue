package com.aimeelina.communityvue.utils;

import com.aimeelina.communityvue.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    //存放用户信息(线程隔离)，用于替代session对象
    ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public void setUser(User user){
        userThreadLocal.set(user);
    }

    public User getUser(){
        return userThreadLocal.get();
    }

    public void clear(){
        userThreadLocal.remove();
    }

}
