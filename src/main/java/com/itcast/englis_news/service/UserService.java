package com.itcast.englis_news.service;

import com.itcast.englis_news.common.User;

public interface UserService {


    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}

