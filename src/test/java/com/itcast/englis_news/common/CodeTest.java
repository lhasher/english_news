package com.itcast.englis_news.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;

public class CodeTest {
    public static void main(String[] args) {
        LineCaptcha pic01 = CaptchaUtil.createLineCaptcha(150, 100);
        pic01.write("d:/pic01.png");
        Console.log(pic01.getCode());
        System.out.println(pic01.verify("1234"));
    }
}
