package com.itcast.englis_news.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.itcast.englis_news.dao.RoleMapper;
import com.itcast.englis_news.common.Role;
import com.itcast.englis_news.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Integer rid) {
        return roleMapper.selectByPrimaryKey(rid);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

}
