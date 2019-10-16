package com.laohu.springboot.mybatistran.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @program: springboot
 * @description: Springboot集成Mybatis插件
 * @author: Holland
 * @create: 2019-10-16 11:11
 **/
//定义拦截签名
@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class,Integer.class}
        )})
public class MyPlugin implements Interceptor {

    Properties properties;

    /**
     * 拦截方法的逻辑
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("mybatis插件的拦截方法:"+invocation.getMethod());
        return invocation.proceed();
    }

    /**
     * 生成mybatis拦截器代理对象
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("包装的对象:"+target.getClass().getName());
        return Plugin.wrap(target,this);
    }

    /**
     * 设置插件属性
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println(properties);
        this.properties = properties;
    }
}
