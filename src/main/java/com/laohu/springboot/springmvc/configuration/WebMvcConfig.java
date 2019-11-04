package com.laohu.springboot.springmvc.configuration;

import com.laohu.springboot.springmvc.interceptor.Interceptor1;
import com.laohu.springboot.springmvc.interceptor.MultiInterceptor1;
import com.laohu.springboot.springmvc.interceptor.MultiInterceptor2;
import com.laohu.springboot.springmvc.interceptor.MultiInterceptor3;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: springboot
 * @description: MVC配置类
 * @author: Holland
 * @create: 2019-11-01 16:18
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册单个自定义拦截器,并制定拦截匹配模式
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册拦截器到MVC机制,然后会返回一个拦截器注册
//        InterceptorRegistration ir = registry.addInterceptor(new Interceptor1());
//        //指定拦截匹配模式,限制拦截器拦截请求
//        ir.addPathPatterns("/interceptor/*");
//    }

    /**
     * 注册多个自定义拦截器,并制定拦截匹配模式
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册拦截器到MVC机制,然后会返回一个拦截器注册
//        InterceptorRegistration ir = registry.addInterceptor(new MultiInterceptor1());
//        //指定拦截匹配模式,限制拦截器拦截请求
//        ir.addPathPatterns("/interceptor/*");
//        //注册拦截器到MVC机制,然后会返回一个拦截器注册
//        InterceptorRegistration ir2 = registry.addInterceptor(new MultiInterceptor2());
//        //指定拦截匹配模式,限制拦截器拦截请求
//        ir.addPathPatterns("/interceptor/*");
//        //注册拦截器到MVC机制,然后会返回一个拦截器注册
//        InterceptorRegistration ir3 = registry.addInterceptor(new MultiInterceptor3());
//        //指定拦截匹配模式,限制拦截器拦截请求
//        ir.addPathPatterns("/interceptor/*");
//    }
}
