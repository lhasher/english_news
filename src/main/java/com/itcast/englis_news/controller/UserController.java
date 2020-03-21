package com.itcast.englis_news.controller;

import com.itcast.englis_news.common.Response;
import com.itcast.englis_news.common.ResponseStatus;
import com.itcast.englis_news.common.User;
import com.itcast.englis_news.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/save")
    public Response<String> register(@RequestBody User user){
        userService.insert(user);
        Response<String> response = new Response<>();
        response.setStatus_code(ResponseStatus.CODE_200);
        response.setData("注册成功!");
        return response;
    }

    @RequestMapping("/one")
    public User getOne(){
        User user = new User();
        user.setUname("张三");
        user.setAge(12);
        user.setEmail("863577223@qq.com");
        user.setPhoneNum("13163242772");
        user.setSex("男");
        user.setPassword("123123");
        return user;
    }
}
