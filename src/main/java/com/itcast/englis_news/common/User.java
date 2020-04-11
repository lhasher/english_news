package com.itcast.englis_news.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    /**
     * 主键ID
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 账号密码
     */
    private String password;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注册时间
     */
    private Date birthday;

    /**
     * 注册时间
     */
    private Date registerDate;

    /**
     * 上次登录时间
     */
    private Date lastLoginDate;

    private static final long serialVersionUID = 1L;
}