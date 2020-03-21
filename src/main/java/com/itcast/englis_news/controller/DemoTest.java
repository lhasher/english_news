package com.itcast.englis_news.controller;

import com.itcast.englis_news.common.Goods;
import com.itcast.englis_news.service.IGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class DemoTest {
    private final IGoods iGoods;

    @Autowired
    public DemoTest(IGoods goodsService) {
        this.iGoods = goodsService;
    }

    @GetMapping("/test")
    public ModelAndView testDemo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        mv.addObject("zhangsan", 123);
        return mv;
    }

    @RequestMapping("/all")
    public List<Goods> test02() {
        List<Goods> goodsList = iGoods.findAll();
//        System.out.println(goodsList.toString());
        return goodsList;
    }
    @GetMapping("/single/{id}")
    public Goods test03(@PathVariable int id){
        return iGoods.findByID(id);
    }

    @GetMapping("/s/{a}/{b}/{c}")
    public String test04(@PathVariable int a,@PathVariable String b,@PathVariable boolean c ){
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        return "success";
    }
}
