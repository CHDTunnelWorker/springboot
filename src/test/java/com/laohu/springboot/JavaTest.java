package com.laohu.springboot;

import com.laohu.springboot.AOP.appointProgramming.HelloService;
import com.laohu.springboot.AOP.appointProgramming.HelloServiceImpl;
import com.laohu.springboot.AOP.appointProgramming.MyInterceptor;
import com.laohu.springboot.AOP.appointProgramming.ProxyBean;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * @program: springboot
 * @description: 普通java代码的测试
 * @author: Holland
 * @create: 2019-10-11 17:33
 **/
public class JavaTest {
    @Test
    public void testAopProxy(){
        HelloService helloService = new HelloServiceImpl();
        //按约定获取bean
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("胡伟康");
        System.out.println("#####################name is null !!!");
        proxy.sayHello(null);
    }

    @Test
    public void generatePassword(){
        String msg = "abc";
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("uvwxyz");
        System.out.println(passwordEncoder.encode(msg));
    }
}
