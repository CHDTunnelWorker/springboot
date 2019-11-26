package com.laohu.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program: springboot
 * @description: 自定义进行用户验证的配置类
 * @author: Holland
 * @create: 2019-11-26 14:53
 **/
@Configuration
public class CustomAuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }
}
