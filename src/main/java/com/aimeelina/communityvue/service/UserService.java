package com.aimeelina.communityvue.service;

import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUser(int offSet,int maxLine){
        return userMapper.selectAll(offSet,maxLine);
    }

    public int countUser(){return userMapper.getRowsNumber();}



}
