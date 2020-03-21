package com.itcast.englis_news.dao;

import com.itcast.englis_news.common.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectAll();
}