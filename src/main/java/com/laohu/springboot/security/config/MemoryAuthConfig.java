package com.laohu.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program: springboot
 * @description: 使用内存签名服务进行验证(不是主要的验证方式,仅为测试用)
 * @author: Holland
 * @create: 2019-11-26 11:00
 **/
//@Configuration
public class MemoryAuthConfig extends WebSecurityConfigurerAdapter {

    /**
     * @Description: 配置用户信息以及角色名称
     * @param: [auth]
     * @return: void
     * @auther: Holland
     * @date: 2019/11/26 11:03
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //创建密码编码器,在配置过程中必须使用该编码器 否则会发生异常,该编码加密为单向不可逆
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //使用内存存储并设置密码编码器
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userConfig =
                auth.inMemoryAuthentication().passwordEncoder(passwordEncoder);
        //注册用户admin,密码为abc,并赋予user和admin权限
        userConfig.withUser("admin").password("$2a$10$1/Kfhpoy.9La2wsyc9rV.ewnNgoIt5QRrZH8/VSd4N3t9EvMBmjPq")
                .authorities("ROLE_USER","ROLE_ADMIN");
        //注册用户myuser,密码为123456,赋予user权限
        userConfig.withUser("myuser").password("$2a$10$ZBZenkw/xVS3mHSzc799FuA5eyKmj7D9v0sSP9eF8q8c8LUxkQfCu")
                .authorities("ROLE_USER");
    }
}
