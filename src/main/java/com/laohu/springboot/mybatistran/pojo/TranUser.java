package com.laohu.springboot.mybatistran.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * @program: springboot
 * @description: 数据库事务测试用户类
 * @author: Holland
 * @create: 2019-10-16 12:24
 **/
@Data
@Alias("tranUser")
@Accessors(chain = true)
public class TranUser {
    private Long id;
    private String userName;
    private String note;
}
