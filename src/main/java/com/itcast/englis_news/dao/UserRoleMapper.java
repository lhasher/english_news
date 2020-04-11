package com.itcast.englis_news.dao;

import com.itcast.englis_news.common.UserRole;import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    int deleteByPrimaryKey(@Param("id") Integer id, @Param("useUid") Integer useUid, @Param("rolRid") Integer rolRid);
}