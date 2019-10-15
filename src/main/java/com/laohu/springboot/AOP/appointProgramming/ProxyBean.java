package com.laohu.springboot.AOP.appointProgramming;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: springboot
 * @description: 代理对象生成类
 * @author: Holland
 * @create: 2019-10-11 16:25
 **/
public class ProxyBean implements InvocationHandler {
    private Object target = null;
    private Interceptor interceptor = null;

    /**
     * 绑定代理对象
     * @param target 被代理的对象
     * @param interceptor 拦截器
     * @return 代理对象
     */
    public static Object getProxyBean(Object target,Interceptor interceptor){
        ProxyBean proxyBean = new ProxyBean();
        //保存被代理的对象
        proxyBean.target = target;
        //保存拦截器
        proxyBean.interceptor = interceptor;
        //生成代理对象
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), proxyBean);
        System.out.println(proxy.getClass().getName()+proxy.toString());
        return proxy;
    }

    /**
     * 处理代理对象的方法逻辑
     * @param proxy 代理对象
     * @param method 当前调用的方法
     * @param args 方法所需的参数
     * @return 方法调用结果
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(proxy, method, args);
        Object retObj = null;
        try {
            if(this.interceptor.before()){
                retObj = this.interceptor.around(invocation);
            } else {
                retObj = method.invoke(target,args);
            }
        } catch (Exception e) {
            //产生异常
            exceptionFlag =true;
        }
        this.interceptor.after();
        if(exceptionFlag){
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturning();
            return retObj;
        }
        return null;
    }
}
