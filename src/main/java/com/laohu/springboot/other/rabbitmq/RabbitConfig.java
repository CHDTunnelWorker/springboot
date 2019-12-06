package com.laohu.springboot.other.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot
 * @description: rabbitMQ配置类
 * @author: Holland
 * @create: 2019-12-02 10:20
 **/
@Configuration
public class RabbitConfig {
    /**
     * 消息队列名称
     */
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;
    /**
     * 用户对象队列名称
     */
    @Value("${rabbitmq.queue.user}")
    private String userQueueName;

    @Bean
    public Queue createQueueMsg(){
        //创建字符串的消息队列
        return new Queue(msgQueueName,true);
    }
}
