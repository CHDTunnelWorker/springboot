package com.laohu.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * @program: springboot
 * @description: 自定义进行用户验证的配置类(项目开发中主要采用此配置)
 * @author: Holland
 * @create: 2019-11-26 14:53
 **/
@Configuration
public class CustomAuthConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注入配置的秘钥
     */
    @Value("${param.secret}")
    private String secret;
    /**
     * 自动注入userDetailsService实现类
     */
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //创建自定义秘钥的密码编译器
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(secret);
        //设置密码编译器以及用户密码验证服务
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * @Description: 使用ant风格配置访问权限限定,遵守先配置原则,即最先配置的访问规则优先启用,后面的与前面的规则冲突的以先配置的为准
     * 在实际工作中,把具体的配置放到前面配置,不具体的配置放到后面
     * @param: [http]
     * @return: void
     * @auther: Holland
     * @date: 2019/11/28 10:02
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                //限定"/user/welcome","/user/details" 路径请求,赋予给ROLE_ADMIN 和 ROLE_USER角色,hasAnyRole方法会默认在角色名称前面
//                //添加ROLE_ 所以只需要填写USER,ADMIN即可
//                .antMatchers("/user/welcome","/user/details").hasAnyRole("USER","ADMIN")
//                //限定/admin/路径后面所有请求赋予给 ROLE_ADMIN 角色,hasAuthority就需要填全部角色名称了
//                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//                //表示其他路径只要通过认证就可以访问
//                .anyRequest().permitAll()
//                //对于没有配置权限的其他请求允许匿名访问
//                .and().anonymous()
//                //采用spring security 默认的登录页面
//                .and().formLogin()
//                //启动http基础验证
//                .and().httpBasic();
//    }


    /**
     * @Description: 使用Spring表达式配置访问权限,主要依赖access()方法,返回true 允许访问 返回false 不允许访问
     * 该配置包含:
     * 1.access配置方式
     * 2.强制https请求配置
     * 3.关闭CSRF(防止跨站点请求伪造攻击功能),该功能默认配置开启(推荐开启)
     * 4.开启记住我功能并对时长和键进行配置
     * 5.设置自定义的登录页面,以及登录成功后跳转的页面(但是要对路径跳转的页面配置映射)
     * 6.登出(springsecurity会提供一个默认路径"/logout"),这个路径可以自定义
     *
     * 注意事项:
     * 1.对于登录,spring security规定请求必须是POST请求,且参数用户名必须是username,密码为password,记住我必须是remember-me且是
     * checkbox格式;
     * 2.对于登出,必须为POST请求
     * @param: [http]
     * @return: void
     * @auther: Holland
     * @date: 2019/11/28 10:37
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //限定/user/** 请求 角色限定为ROLE_ADMIN ROLE_USER
                .antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN')")
                //限定/admin/welcome1 角色限定为ROLE_ADMIN,并且isFullyAuthenticated方法限定非记住我方式登录
                .antMatchers("/admin/welcome1").access("hasAnyAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
                //限定/admin/welcome2 角色限定为ROLE_ADMIN 可以使用记住我方式登录
                .antMatchers("/admin/welcome2").access("hasAnyAuthority('ROLE_ADMIN')")
                //开启记住我功能(有效时间为86400秒,即一天;remember-me-key 作为cookie中保存该属性的键)
                .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
                //采用自定义路径的登录页面/login/page,且设置登录成功跳转的页面/admin/welcome1(但是要对路径跳转的页面配置映射关系)
                //具体配置关系见WebConfig类中的配置
                .and().formLogin().loginPage("/login/page").defaultSuccessUrl("/admin/welcome1")
                //配置自定义登出路径和默认的成功后跳转路径(jsp映射关系需要配置)
                .and().logout().logoutUrl("/logout/page").logoutSuccessUrl("/welcome")
                //启动http基础验证
                .and().httpBasic()
                //强制该路径下的请求必须是https
                .and().requiresChannel().antMatchers("/admin/**").requiresSecure()
                //不使用https请求
                .and().requiresChannel().antMatchers("/user/**").requiresInsecure()
                //关闭防止跨站点请求伪造攻击功能
                .and().csrf().disable();
    }
}
