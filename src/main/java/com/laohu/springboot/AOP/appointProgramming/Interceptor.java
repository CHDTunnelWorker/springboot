package com.laohu.springboot.AOP.appointProgramming;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: springboot
 * @description: 拦截器接口,模拟AOP的事件
 * @author: Holland
 * @create: 2019-10-1 15:42
 **/
public interface Interceptor {
    /**
     * 事前方法
     */
    public boolean before();
    /**
     * 事后方法
     */
    public void after();

    /**
     * @param invocation --回调参数,可以通过其proceed方法,回调原有事件
     * @return 原有事件返回对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object around(Invocation invocation)
            throws InvocationTargetException,IllegalAccessException;
    /**
     * 事后返回方法,事后未发生异常执行
     */
    public void afterReturning();
    /**
     * 事后异常方法,事后出现异常执行
     */
    public void afterThrowing();
    /**
     * 是否使用around方法取代原有的方法
     */
    boolean useAround();

}
