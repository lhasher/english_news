package com.itcast.englis_news.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response <T>{
    //返回状态码
    private ResponseStatus status_code;
    //返回数据
    private T data;
}
