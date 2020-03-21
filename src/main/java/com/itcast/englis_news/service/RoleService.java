package com.itcast.englis_news.service;

import com.itcast.englis_news.common.Role;
public interface RoleService{


    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

}
