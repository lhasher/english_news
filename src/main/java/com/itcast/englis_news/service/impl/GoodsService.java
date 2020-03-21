package com.itcast.englis_news.service.impl;

import com.itcast.englis_news.common.Goods;
import com.itcast.englis_news.dao.GoodsMapper;
import com.itcast.englis_news.service.IGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService implements IGoods {
    private final GoodsMapper goodsMapper;

    @Autowired
    public GoodsService(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public List<Goods> findAll() {
        return goodsMapper.selectAll();
    }

    @Override
    public Goods findByID(int id){
        return goodsMapper.selectByPrimaryKey(id);
    }
}
