package com.itcast.englis_news.service;

import com.itcast.englis_news.common.Privilege;
public interface PrivilegeService{


    int deleteByPrimaryKey(Integer pid);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);

}
