package com.itcast.englis_news.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.itcast.englis_news.common.User;
import com.itcast.englis_news.common.result.Result;
import com.itcast.englis_news.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    //使用构造方法注入bean
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册用户
     * @param params 用户信息以及验证码
     * @return 注册完成返回的结果
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result register(@RequestBody Map<String,Object> params) {
        JSONObject jsonObject = JSONUtil.parseFromMap(params);
        User user = jsonObject.get("user", User.class);
        String code = jsonObject.get("code", String.class);
        System.out.println(user+"->:"+code);
        userService.insert(user,code);
        return Result.ok();
    }


    /**
     * 登陆,已经配置spring security进行安全验证
     * @param user 用户
     * @return 登陆是否成功
     */
    @RequestMapping(value = "/login")
    public Result login(@RequestBody User user){
        System.out.println(user);
        return Result.ok();
    }

    /**
     * 发送验证码
     * @param email 用户邮箱
     * @return 验证码封装的结果集
     */
    @RequestMapping("/sendVerifyCode")
    public Result sendVerifyCode(@RequestBody String email){
        String verifyCode=userService.sendVerifyCode(email);
        return Result.ok().data("verifyCode",verifyCode);
    }
}
