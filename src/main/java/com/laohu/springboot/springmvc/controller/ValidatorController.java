package com.laohu.springboot.springmvc.controller;

import com.laohu.springboot.springmvc.pojo.ValidatorPojo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description: MVC数据验证机制的测试控制器
 * @author: Holland
 * @create: 2019-10-30 16:41
 **/
@RequestMapping("/validator")
@Controller
public class ValidatorController {


    /**
     * @Description: 测试使用java自带以及Hibenate支持的验证注解
     * @param: [vp, errors]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: Holland
     * @date: 2019/10/31 10:52
     */
    @RequestMapping("/annoValid")
    @ResponseBody
    public Map<String,Object> validatorPojo(
            @Valid @RequestBody ValidatorPojo vp, Errors errors
    )
    {
        Map<String,Object> errMap = new HashMap<>();
        //获取错误列表
        List<ObjectError> allErrors = errors.getAllErrors();
        for(ObjectError oe : allErrors){
            String key = null;
            String msg = null;
            //如果是字段错误
            if(oe instanceof FieldError){
                //将错误对象转换成字段错误对象
                FieldError fe = (FieldError) oe;
                //获取错误验证字段名
                key = fe.getField();
                System.out.println(key+"字段错误");
            } else {
                //非字段错误,获取验证对象的名称
                key = oe.getObjectName();
                System.out.println(key+"非字段错误");
            }
            //获取错误信息
            msg = oe.getDefaultMessage();
            errMap.put(key,msg);
        }
        return errMap;
    }

}
