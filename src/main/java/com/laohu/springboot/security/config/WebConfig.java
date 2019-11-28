package com.laohu.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: springboot
 * @description: MVC配置
 * @author: Holland
 * @create: 2019-11-28 11:58
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * @Description: 新增注册路径映射关系
     * @param: [registry]
     * @return: void
     * @auther: Holland
     * @date: 2019/11/28 11:59
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //使得/login/page访问路径映射到login.jsp
        registry.addViewController("/login/page").setViewName("login");
        //使得/logout/page访问路径映射到logout_welcome.jsp
        registry.addViewController("/logout/page").setViewName("logout_welcome");
        //使得/logout访问路径映射到logout.jsp
        registry.addViewController("/logout").setViewName("logout");
    }
}
