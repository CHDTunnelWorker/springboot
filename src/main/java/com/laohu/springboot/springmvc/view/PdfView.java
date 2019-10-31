package com.laohu.springboot.springmvc.view;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @program: springboot
 * @description: PDF导出视图类
 * @author: Holland
 * @create: 2019-10-31 15:52
 **/
public class PdfView extends AbstractPdfView {

    //导出服务接口
    private PdfExportService pdfExportService = null;

    /**
     * 创建该对象时,构造注入导出服务接口
     * @param pdfExportService
     */
    public PdfView(PdfExportService pdfExportService){
        this.pdfExportService = pdfExportService;
    }

    /**
     *  调用接口的实现
     * @param model
     * @param document
     * @param writer
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
        HttpServletRequest request, HttpServletResponse response) throws Exception {
        pdfExportService.make(model,document,writer,request,response);
    }

}
