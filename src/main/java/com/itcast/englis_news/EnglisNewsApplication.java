package com.itcast.englis_news;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itcast.englis_news.dao")
public class EnglisNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglisNewsApplication.class, args);
    }

}
