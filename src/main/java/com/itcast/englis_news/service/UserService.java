package com.itcast.englis_news.service;

import com.itcast.englis_news.common.User;
import com.itcast.englis_news.common.exception.BaseException;

public interface UserService {


    int deleteByPrimaryKey(Integer uid);

    void insert(User record, String code) throws BaseException;

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    String sendVerifyCode(String email) throws BaseException;

}

