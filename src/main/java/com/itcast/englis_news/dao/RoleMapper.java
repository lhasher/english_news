package com.itcast.englis_news.dao;

import com.itcast.englis_news.common.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByUserID(Integer uid);
}