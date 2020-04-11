package com.itcast.englis_news.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一响应结果类
 * 构思:统一结果一般有5个信息
 * 1.是否响应成功
 * 2.响应状态码
 * 3.响应消息
 * 4.响应数据
 * 5.其他标识符
 * 响应考虑方便,采用链式变成,使用map封装结果数据
 */

@Data
public class Result {
    /**
     * 返回是否成功
     */
    private Boolean success;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据结果
     */
    private Map<String,Object> data = new HashMap<>();

    /**
     * 限制开发使用下面的统一返回形式
     */
    private Result(){
    }

    /**
     * 通用返回成功
     * @return 返回该类封装结果
     */
    public static Result ok(){
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    /**
     * 通用返回未知错误
     * @return 返回封装的错误结果
     */
    public static Result error(){
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.UNKNOWN_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return r;
    }

    /**
     * 设置结果,形参为结果枚举
     * @param result 结果枚举类对象
     * @return 返回结果
     */
    public static Result setResult(ResultCodeEnum result){
        Result r = new Result();
        r.setSuccess(result.getSuccess());
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }

    /**
     * 通用的设置返回数据
     * @param key 数据名
     * @param value 数据内容
     * @return 封装的结果对象
     */
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    /**
     * 自定义返回是否成功
     * @param success 是否成功
     * @return 返回结果
     */
    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 自定义状态码
     * @param code 状态码
     * @return 返回结果
     */
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 自定义返回信息
     * @param message 信息
     * @return 返回结果
     */
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }


}
