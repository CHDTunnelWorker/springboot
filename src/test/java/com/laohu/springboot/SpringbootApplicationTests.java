package com.laohu.springboot;

import com.laohu.springboot.AOP.appointProgramming.HelloService;
import com.laohu.springboot.AOP.appointProgramming.HelloServiceImpl;
import com.laohu.springboot.AOP.appointProgramming.MyInterceptor;
import com.laohu.springboot.AOP.appointProgramming.ProxyBean;
import com.laohu.springboot.redis.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * 测试通过redisTemplate向redis中保存数据
     * 如果不指定序列化器,会默认采用JdkSerializationRedisSerializer序列化器,会使我们保存的数据以16进制显示,看不到具体字符串
     * 因此:需要指定字符串序列化器
     */
    @Test
    public void testRedisTemplate(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForHash().put("hash","field","hvalue");
    }
}
