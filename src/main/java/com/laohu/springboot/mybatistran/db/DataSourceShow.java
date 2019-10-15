package com.laohu.springboot.mybatistran.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @program: springboot
 * @description: 检测数据库连接池类型
 * @author: Holland
 * @create: 2019-10-15 17:30
 **/
@Component
public class DataSourceShow implements ApplicationContextAware {

    ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println("------------------------");
        System.out.println(dataSource.getClass().getName());
        System.out.println("------------------------");
    }
}
