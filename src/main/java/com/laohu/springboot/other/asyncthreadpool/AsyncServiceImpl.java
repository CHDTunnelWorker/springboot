package com.laohu.springboot.other.asyncthreadpool;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @program: springboot
 * @description: 异步方法接口的实现类
 * @author: Holland
 * @create: 2019-11-28 14:36
 **/
@Service
public class AsyncServiceImpl implements AsyncService{

    /**
     * @Description:使用异步声明调用该方法
     * @param: []
     * @return: void
     * @auther: Holland
     * @date: 2019/11/28 14:37
     */
    @Override
    @Async
    public void generateReport() {
        System.out.println("报表线程名称:"+"["+Thread.currentThread().getName()+"]");
    }
}
