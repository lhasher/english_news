package com.itcast.englis_news.service;

import com.itcast.englis_news.common.Goods;

import java.util.List;

public interface IGoods {
    List<Goods> findAll();
    Goods findByID(int id);
}
