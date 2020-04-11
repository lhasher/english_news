package com.itcast.englis_news.common.result;

import lombok.Getter;

/**
 * 统一响应结果状态
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(true, 20000, "成功"),
    UNKNOWN_ERROR(false, 20001, "未知错误"),
    PARAM_ERROR(false, 20002, "参数错误"),
    NULL_POINT_ERROR(false,20003,"空指针异常"),
    MESSAGING_ERROR(false,20004,"发送邮件错误"),
    CODE_INVALID_ERROR(false,20005,"验证码错误"),
    DB_OPERATION_ERROR(false,20006,"数据库异常"),
    IO_ERROR(false,20006,"IO异常");

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    //响应是否成功
    private Boolean success;
    //响应状态码
    private Integer code;
    //响应信息
    private String message;

}
