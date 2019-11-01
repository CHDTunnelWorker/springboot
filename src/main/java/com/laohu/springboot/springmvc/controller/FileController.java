package com.laohu.springboot.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot
 * @description: 文件上传测试
 * @author: Holland
 * @create: 2019-11-01 14:28
 **/
@RequestMapping("/file")
@Controller
public class FileController {

    /**
     * @Description: 采用HttpServletRequest接口来接收上传文件的请求对象MultipartHttpServletRequest的实现类Standard,该
     *  接口继承了HttpServletRequest和MultipartRequest接口,并扩展了文件的操作功能
     *
     *  该接口经过测试不可使用!
     * @param: [request]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: Holland
     * @date: 2019/11/1 14:35
     */
    @RequestMapping("/upload/request")
    @ResponseBody
    public Map<String,Object> uploadRequest(HttpServletRequest request){
        boolean flag = false;
        MultipartHttpServletRequest mreq = null;
        //强制转换为MultipartHttpServletRequest接口对象
        if(mreq instanceof MultipartHttpServletRequest){
            mreq = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false,"上传失败!");
        }
        //获取MultipartFile文件信息
        MultipartFile mf = mreq.getFile("file");
        //获取原文件名称
        String filename = mf.getOriginalFilename();
        File file = new File(filename);
        try {
            mf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false,"上传失败!");
        }
        return dealResultMap(true,"上传成功!");
    }

    /**
     * @Description: 使用MVC的MultipartFile类作为参数
     * @param: [file]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: Holland
     * @date: 2019/11/1 15:06
     */
    @RequestMapping("/upload/multipart")
    @ResponseBody
    public Map<String,Object> uploadMultipartFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        File dest = new File(filename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false,"上传失败!");
        }
        return dealResultMap(true,"上传成功!");
    }

    /**
     * @Description: 使用java 自带的Servlet包下的Part接口对象直接写入,推荐使用这种方式接受上传的文件
     * @param: [file]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: Holland
     * @date: 2019/11/1 15:06
     */
    @RequestMapping("/upload/part")
    @ResponseBody
    public Map<String,Object> uploadPart(Part file){
        String fileName = file.getSubmittedFileName();
        try {
            file.write(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false,"上传失败!");
        }
        return dealResultMap(true,"上传成功!");
    }

    /**
     * @Description: 处理上传文件的结果
     * @param: [success, msg]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: Holland
     * @date: 2019/11/1 14:32
     */
    private Map<String,Object> dealResultMap(boolean success,String msg){
        Map<String,Object> result = new HashMap<>();
        result.put("success",success);
        result.put("msg",msg);
        return result;
    }
}
