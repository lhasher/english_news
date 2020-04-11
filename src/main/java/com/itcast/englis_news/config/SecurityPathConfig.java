package com.itcast.englis_news.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "path-manager.security-config-path")
public class SecurityPathConfig {
    /**
     * 登陆页面
     */
    private String loginPage;
    /**
     * 登陆请求url
     */
    private String loginUrl;
    /**
     * 登陆成功后跳转的url
     */
    private String loginSuccessUrl;
    /**
     * 登出成功跳转url
     */
    private String logoutSuccessUrl;
    /**
     * 放行资源集
     * #注意对于集合/map/数组,需要提前创建一个空对象才能赋值成功
     */
    private String[] antMatchers=new String[0];
}
