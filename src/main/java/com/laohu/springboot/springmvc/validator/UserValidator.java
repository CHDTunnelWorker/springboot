package com.laohu.springboot.springmvc.validator;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @program: springboot
 * @description: 自定义用户对象参数验证器,需要实现Spring框架下的Validator验证器接口
 * @author: Holland
 * @create: 2019-10-31 10:23
 *
 * 光定义这个自定义验证器还不够,还需要将其加入到spring中,采用注解@InitBinder标注在方法上,会在执行控制器方法前,先执行带有该注解的方法
 * 我们将WebDataBinder对象作为参数传入,调用它的setValidator方法,将自定义验证器加入
 **/
public class UserValidator implements Validator {

    /**
     * 判定当前验证器是否支持该class类型的验证
     * @param clazz --pojo类型
     * @return 当前验证器是否支持该pojo验证
     *
     * 当前该验证器只验证TranUser对象
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(TranUser.class);
    }

    /**
     * 如果上面的supports方法返回的结果为true,则调用这个方法执行验证逻辑
     * @param target 被验证的pojo对象
     * @param errors 错误对象
     */
    @Override
    public void validate(Object target, Errors errors) {
        //对象整体为空
        if(target == null){
            //直接在参数处报错,不进入控制器方法
            errors.rejectValue("",null,"用户不能为空");
            return;
        }
        //强转
        TranUser user = (TranUser) target;
        //用户名为空
        if(StringUtils.isEmpty(user.getUserName())){
            //增加错误,可以进入控制器方法
            errors.rejectValue("userName",null,"用户不能为空");
        }
    }
}
