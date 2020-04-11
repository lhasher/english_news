package com.itcast.englis_news.config;

import cn.hutool.core.io.file.FileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateConfig {

    //注入模板路径,采用相对路径,注意${}注入属性文件
    @Value("${path-manager.email-template-path}")
    private String location;

    //读取邮件模板内容,转换为字符串,随后可以插入响应的动态内容
    public String getTemplateContentAsString() {
        FileReader fileReader = new FileReader(location);
        return fileReader.readString();
    }
}
