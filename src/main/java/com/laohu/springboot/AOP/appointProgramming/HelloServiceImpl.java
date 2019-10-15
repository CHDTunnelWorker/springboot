package com.laohu.springboot.AOP.appointProgramming;

/**
 * @program: springboot
 * @description: 简易接口实现类
 * @author: Holland
 * @create: 2019-10-11 15:43
 **/
public class HelloServiceImpl implements HelloService{

    @Override
    public void sayHello(String name) {
        if(name == null && name.trim() == ""){
            throw new RuntimeException("parameter is null!!!");
        }
        System.out.println("hello,"+name);
    }
}
