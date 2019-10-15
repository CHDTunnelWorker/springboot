package com.laohu.springboot.AOP.appointProgramming;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: springboot
 * @description: 自定义拦截器实现接口
 * @author: Holland
 * @create: 2019-10-11 16:09
 **/
public class MyInterceptor implements Interceptor{
    @Override
    public boolean before() {
        System.out.println("before .............");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after .............");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("aroundBefore .............");
        Object obj = invocation.proceed();
        System.out.println("aroundAfter .............");
        return obj;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning .............");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing .............");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}
