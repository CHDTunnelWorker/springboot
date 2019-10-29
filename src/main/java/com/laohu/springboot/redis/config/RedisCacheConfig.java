package com.laohu.springboot.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * @program: springboot
 * @description: redis缓存管理器配置类
 * @author: Holland
 * @create: 2019-10-28 17:49
 **/
@Configuration
public class RedisCacheConfig {
    /**
     * 注入连接工厂,由springboot自动配置生成
     */
    @Autowired
    private RedisConnectionFactory connectionFactory;

    /**
     * 自定义redis缓存管理器
     * @return
     */
    @Bean("redisCache")
    public RedisCacheManager initRedisCacheManager(){
        //redis加锁的写入器
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        //启动redis默认的缓存配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        //设置jdk序列化器
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
        //设置禁用前缀
        config = config.disableKeyPrefix();
        //设置十分钟超时
        config = config.entryTtl(Duration.ofMinutes(10));
        //创建redis缓存管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
        return redisCacheManager;
    }
}
