package com.laohu.springboot.other.asyncthreadpool;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @program: springboot
 * @description: 异步线程池配置类
 * @author: Holland
 * @create: 2019-11-28 14:23
 **/
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    /**
     * @Description:自定义异步线程池
     * @param: []
     * @return: java.util.concurrent.Executor
     * @auther: Holland
     * @date: 2019/11/28 14:24
     */
    @Override
    public Executor getAsyncExecutor() {
        //创建线程池
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        //设置线程池最大核心线程数
        threadPool.setCorePoolSize(10);
        //设置线程池最大线程数
        threadPool.setMaxPoolSize(30);
        //设置任务队列最大容量
        threadPool.setQueueCapacity(2000);
        //初始化
        threadPool.initialize();
        return threadPool;
    }
}
