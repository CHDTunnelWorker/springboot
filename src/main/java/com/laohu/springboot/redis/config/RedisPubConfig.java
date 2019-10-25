package com.laohu.springboot.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @program: springboot
 * @description: 测试redis的发布订阅，配置监听redis发布的消息
 * @author: Holland
 * @create: 2019-10-21 15:31
 **/
@Configuration
public class RedisPubConfig {
    /**
     * redis客户端
     */
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     *redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory connectionFactory;
    /**
     * redis消息监听器
     */
    @Autowired
    private MessageListener redisMsgListener;
    /**
     * 任务线程池
     */
    private ThreadPoolTaskScheduler taskScheduler;

    /**
     * 创建任务池,运行线程等待处理redis的消息
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler(){
        if(taskScheduler != null){
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /**
     * 定义redis的监听容器
     * @return
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //设置redis连接工厂
        container.setConnectionFactory(connectionFactory);
        //设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        //定义监听渠道名称为topic1
        ChannelTopic topic = new ChannelTopic("topic1");
        //使用监听器监听redis的消息
        container.addMessageListener(redisMsgListener,topic);
        return container;
    }
}
