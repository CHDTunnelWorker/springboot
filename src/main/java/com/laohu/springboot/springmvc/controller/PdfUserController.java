package com.laohu.springboot.springmvc.controller;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import com.laohu.springboot.mybatistran.service.UserService;
import com.laohu.springboot.springmvc.view.PdfExportService;
import com.laohu.springboot.springmvc.view.PdfView;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.awt.*;
import java.util.List;


/**
 * @program: springboot
 * @description: 测试在用户控制器中通过pdf视图导出pdf的用户数据
 * @author: Holland
 * @create: 2019-10-31 16:32
 **/
@RequestMapping("/pdfView")
@Controller
public class PdfUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/export/pdf")
    public ModelAndView exportPdf(String userName,String note){
        //查询用户信息列表
        List<TranUser> userList = userService.findUsers(userName, note);
        //定义pdf视图
        View view = new PdfView(exportService());
        ModelAndView mv = new ModelAndView();
        //设置视图
        mv.setView(view);
        //加入数据模型
        mv.addObject("userList",userList);
        return mv;
    }

    //实现pdf自定义接口,定制自己的pdf格式
    private PdfExportService exportService(){
        return (model, document, pdfWriter, request, response) -> {
            try {
                //A4纸张
                document.setPageSize(PageSize.A4);
                //标题
                document.addTitle("用户信息");
                //换行
                document.add(new Chunk("\n"));
                //设置表格,三列
                PdfPTable table = new PdfPTable(3);
                //单元格
                PdfPCell cell = null;
                //字体设置,蓝色加粗
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                //标题
                cell = new PdfPCell(new Paragraph("id",f8));
                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元格加入表格中
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("user_name",f8));
                //居中对齐
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note",f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                //获取数据模型中的用户列表
                List<TranUser> userList = (List<TranUser>) model.get("userList");
                for(TranUser user : userList){
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(user.getId()+""));
                    cell.setHorizontalAlignment(1);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(user.getUserName()));
                    cell.setHorizontalAlignment(1);
                    table.addCell(cell);
                    String note = user.getNote() == null? "" : user.getNote();
                    cell = new PdfPCell(new Paragraph(note));
                    cell.setHorizontalAlignment(1);
                    table.addCell(cell);
                }
                //在文档中加入表格
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        };
    }
}
