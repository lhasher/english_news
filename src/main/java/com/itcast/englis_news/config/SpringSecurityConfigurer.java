package com.itcast.englis_news.config;

import com.itcast.englis_news.filter.CustomAuthenticationFilter;
import com.itcast.englis_news.service.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration(value = "springSecurityConfigurer")
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Value("#{securityPathConfig.loginPage}")
    private String loginPage;

    @Value("#{securityPathConfig.loginUrl}")
    private String loginProcessingUrl;

    @Value("#{securityPathConfig.loginSuccessUrl}")
    private String loginSuccessUrl;

    @Value("#{securityPathConfig.logoutSuccessUrl}")
    private String logoutSuccessUrl;

    @Value("#{securityPathConfig.antMatchers}")
    private String[] antMatchers;


    @Autowired
    private CustomUserDetailService customUserDetailService;

    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    /**
     * 该配置类配置的时候向容器注入加密器
     * @return 加密器
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return this.passwordEncoder;
    }

    @Bean
    public CustomAuthenticationFilter getCustomAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler());
        filter.setFilterProcessesUrl("/user/login");
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    /**
     * 配置访问资源的处理
     * @param http 请求
     * @throws Exception 处理异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);不能调用父类的该方法,否者报错
        http.cors().and().csrf().disable()
                .formLogin().loginPage(loginPage).loginProcessingUrl(loginProcessingUrl).defaultSuccessUrl(loginSuccessUrl)
                .and()
                .logout().logoutSuccessUrl(logoutSuccessUrl)
                .and()
                .authorizeRequests().antMatchers(antMatchers).permitAll()
                .anyRequest().authenticated()
                .and().addFilterAt(getCustomAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); //用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
    }

    /**
     * 配置使用哪一个验证管理器
     * @param auth 验证管理器
     * @throws Exception 处理异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置使用自定义userDetail以及token中密码加密所用到的加密器
        auth.userDetailsService(customUserDetailService).passwordEncoder(this.passwordEncoder);
    }

}
