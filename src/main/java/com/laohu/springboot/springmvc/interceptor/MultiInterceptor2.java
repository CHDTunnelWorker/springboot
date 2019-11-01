package com.laohu.springboot.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: springboot
 * @description: 测试多个自定义拦截器的顺序
 * @author: Holland
 * @create: 2019-11-01 16:34
 **/
public class MultiInterceptor2 implements HandlerInterceptor {
    /**
     * 处理器处理前拦截处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("["+this.getClass().getSimpleName()+"]处理器前方法");
        //返回true,不会拦截后续的处理
        return true;
    }

    /**
     * 处理器(包含控制器逻辑)执行完成,获得数据与视图模型执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("["+this.getClass().getSimpleName()+"]处理器后方法");
    }

    /**
     * 视图渲染完成,一切完成之后处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("["+this.getClass().getSimpleName()+"]处理器完成方法");
    }
}
