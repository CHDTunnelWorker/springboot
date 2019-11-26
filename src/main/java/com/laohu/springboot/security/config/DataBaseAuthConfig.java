package com.laohu.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;

/**
 * @program: springboot
 * @description: 通过数据库定义用户认证服务(常用)
 * @author: Holland
 * @create: 2019-11-26 11:38
 **/
//@Configuration
public class DataBaseAuthConfig extends WebSecurityConfigurerAdapter {
    /**
     * 自动注入数据源
     */
    @Autowired
    private DataSource dataSource;
    /**
     * 注入配置的秘钥
     */
    @Value("${param.secret}")
    private String secret;
    /**
     * 使用用户名称查询密码(查出用户名 密码和是否生效三个参数进行验证)
     */
    public final String PWD_QUERY = "select user_name,pwd,available from t_users where user_name = ?";
    /**
     * 使用用户名称查询角色信息(查出用户名和对应的角色名,进行赋予)
     */
    public final String ROLE_QUERY = "select u.user_name,r.role_name from t_users u,t_role r,t_user_role ur " +
            "where u.id = ur.user_id and r.id = ur.role_id and u.user_name = ?";
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //通过自定义秘钥获取密码编码器
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(this.secret);
        //启用jdbc方式进行验证
        auth.jdbcAuthentication()
           //设置密码编辑器
           .passwordEncoder(passwordEncoder)
           //设置数据源
           .dataSource(dataSource)
           //查询用户,密码是否一致自动判断
           .usersByUsernameQuery(PWD_QUERY)
           //赋予权限
           .authoritiesByUsernameQuery(ROLE_QUERY);
    }
}
