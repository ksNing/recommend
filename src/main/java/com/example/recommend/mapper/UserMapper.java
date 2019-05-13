package com.example.recommend.mapper;

import com.example.recommend.pojo.User;

import java.util.List;
import java.util.Map;


public interface UserMapper {


    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);



    User selectByPrimaryKey(Integer userId);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /*
    查出数据库中所有的用户
     */
    List<User> queryUsersBySql(Map<String,Object> data);
}