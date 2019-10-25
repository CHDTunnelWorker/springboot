package com.laohu.springboot.redis.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: springboot
 * @description: 自定义redis消息监听器（测试redis发布订阅）
 * @author: Holland
 * @create: 2019-10-21 15:12
 **/
@Component
public class RedisMessageListener implements MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //消息体(对消息进行处理)
        Object msg = redisTemplate.getValueSerializer().deserialize(message.getBody());
        String body = String.valueOf(msg);
        //渠道名称
        String topic = new String(pattern);
        System.out.println(body);
        System.out.println(topic);
    }
}
