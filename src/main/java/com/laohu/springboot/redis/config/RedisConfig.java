package com.laohu.springboot.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @program: springboot
 * @description: redis配置类
 * @author: Holland
 * @create: 2019-10-17 10:42
 **/

/**
 * @Configuration
 * 7.1实践类(简单实践)
 */

public class RedisConfig {

    private RedisConnectionFactory connectionFactory = null;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        if(this.connectionFactory != null){
            return this.connectionFactory;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //配置最大连接空闲数
        poolConfig.setMaxIdle(30);
        //设置最大连接数
        poolConfig.setMaxTotal(50);
        //使劲儿中最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        //创建jedis连接工厂,将连接池配置注入
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setHostName("192.168.206.128");
        connectionFactory.setPort(6379);
        connectionFactory.setPassword("123456");
        this.connectionFactory = connectionFactory;
        //获取单机的redis配置
        RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
        return connectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object,Object> initRedisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        //redisTemplate会自动初始化StringRedisSerializer,可以通过下面方法直接获取
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        //将键和hash的序列化器全部设置为字符串序列化器
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }
}
