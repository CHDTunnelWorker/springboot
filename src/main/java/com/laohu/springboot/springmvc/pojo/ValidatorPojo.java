package com.laohu.springboot.springmvc.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @program: springboot
 * @description: 测试数据验证注解(JSR-303)
 * @author: Holland
 * @create: 2019-10-30 16:20
 **/
@Data
public class ValidatorPojo {
    /**
     * 主键id,测试不能为空
     */
    @NotNull(message = "id不能为空")
    private Long id;
    /**
     * 日期,测试只能是将来的日期,且日期格式化转换,不能为空
     * @Past 表示只能是过去的日期
     */
    @Future(message = "需要一个将来的日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "日期不能为空")
    private Date date;
    /**
     * double值,不能为空,最小值0.1,最大值10000(金融单位)
     */
    @NotNull(message = "金钱不能为空")
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "10000.00")
    private Double doubleValue;
    /**
     * 数值,不为空,最小值为1,最大值为88
     */
    @NotNull
    @Min(value = 1,message = "最小值为1")
    @Max(value = 88,message = "最大值为88")
    private Integer integer;
    /**
     * 限定范围,1-888
     */
    @Range(min = 1,max = 888,message = "范围为1至888")
    private Long range;
    /**
     * 邮箱验证
     */
    @Email
    private String email;
    /**
     * 字符串长度要求在10-20之间
     */
    @Size(min = 10,max = 20,message = "字符串长度要求10-20之间")
    private String size;
}
