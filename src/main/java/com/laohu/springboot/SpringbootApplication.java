package com.laohu.springboot;

import com.laohu.springboot.mybatistran.plugin.MyPlugin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import java.util.Properties;

@SpringBootApplication
//定义mybatis扫描的包
@MapperScan(
        //指定扫描路径
        basePackages = "com.laohu.springboot.mybatistran.dao",
        //限定扫描的注解接口(不常用)
        annotationClass = Repository.class
)
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    /**
     * springboot会自动默认注入这个SqlSessionFactory
     */
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;

    /**
     * 启动spring bean的生命周期执行方法 ,加入自定义的mybatis插件
     */
//    @PostConstruct
//    public void initMybatis(){
//        //创建自定义插件的对象
//        MyPlugin myPlugin = new MyPlugin();
//        //设置插件属性
//        Properties properties = new Properties();
//        properties.setProperty("key1","value1");
//        properties.setProperty("key1","value1");
//        properties.setProperty("key1","value1");
//        myPlugin.setProperties(properties);
//        //在sqlsessionfactory中添加插件
//        sqlSessionFactory.getConfiguration().addInterceptor(myPlugin);
//    }

    /**
     * 注入事务管理器,由springboot自动生成
     */
    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 使用生命周期的后初始化方法,观察自动生成的事务管理器
     */
    @PostConstruct
    public void viewTransactionManager(){
        System.out.println(transactionManager.getClass().getName());
    }
}
