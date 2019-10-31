package com.laohu.springboot.springmvc.view;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @program: springboot
 * @description: 定义pdf导出接口,任何控制器只要实现这个接口,就能够自定义pdf的导出逻辑
 * @author: Holland
 * @create: 2019-10-31 15:42
 **/
public interface PdfExportService {
    /**
     * 自定义pdf导出的接口
     * @param model 数据模型
     * @param document 代表一个pdf文档
     * @param pdfWriter pdf写入器
     * @param request 请求对象
     * @param response 响应对象
     */
    public void make(Map<String,Object> model, Document document, PdfWriter pdfWriter,
                     HttpServletRequest request, HttpServletResponse response);

}
