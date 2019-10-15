package com.laohu.springboot.AOP.appointProgramming;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: springboot
 * @description: 回调对象
 * @author: Holland
 * @create: 2019-10-11 16:01
 **/
@Data
public class Invocation {
    /**
     * 参数数组
     */
    private Object[] params;
    /**
     * 反射方法对象
     */
    private Method method;
    /**
     * 调用的目标对象
     */
    private Object target;

    public Invocation(){

    }

    public Invocation(Object target,Method method,Object[] params){
        this.target = target;
        this.method = method;
        this.params = params;
    }

    /**
     * proceed 方法
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target,params);
    }
}
