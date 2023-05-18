package com.aimeelina.communityvue.mapper;

import com.aimeelina.communityvue.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectById(int id);
    User selectByName(String name);
    User selectByEmail(String email);
    User selectByPhone(String phone);
    List<User> selectAll(int offSet,int maxLine);
    int insertUser(User user);
    int updateStatus(int id,int status);
    int updateHeader(int id,String headerUrl);
    int updatePassword(int id,String password);

    int getRowsNumber();


}
