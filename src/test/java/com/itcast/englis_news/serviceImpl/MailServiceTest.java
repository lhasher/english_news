package com.itcast.englis_news.serviceImpl;

import com.itcast.englis_news.service.impl.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Value("lhasher@hotmail.com")
    private String destination;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail(destination, "test", "这是一封测试邮件,请忽略!!!");
    }

    @Test
    public void sendHtmlMail() throws MessagingException {
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>这是一封测试邮件,请勿回复!!!</h3>\n" +
                "<h1>html</h1>\n" +
                "<body>\n" +
                "</html>\n";
        mailService.sendHtmlMail(destination, "test01", content);
    }

}
