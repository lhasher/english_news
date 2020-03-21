package com.itcast.englis_news.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.itcast.englis_news.dao.PrivilegeMapper;
import com.itcast.englis_news.common.Privilege;
import com.itcast.englis_news.service.PrivilegeService;
@Service
public class PrivilegeServiceImpl implements PrivilegeService{

    @Resource
    private PrivilegeMapper privilegeMapper;

    @Override
    public int deleteByPrimaryKey(Integer pid) {
        return privilegeMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int insert(Privilege record) {
        return privilegeMapper.insert(record);
    }

    @Override
    public int insertSelective(Privilege record) {
        return privilegeMapper.insertSelective(record);
    }

    @Override
    public Privilege selectByPrimaryKey(Integer pid) {
        return privilegeMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int updateByPrimaryKeySelective(Privilege record) {
        return privilegeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Privilege record) {
        return privilegeMapper.updateByPrimaryKey(record);
    }

}
