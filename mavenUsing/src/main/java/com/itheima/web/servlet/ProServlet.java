package com.itheima.web.servlet;


import com.itheima.service.ProService;
import com.itheima.service.impl.ProServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/proServlet")
public class ProServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String sel = req.getParameter("sel");
//        sel = new String(sel.getBytes("ISO-8859-1"), "GBK");
        System.out.println("sel = " +sel);
        ProService proService = new ProServiceImpl();
        String proJson = proService.findProToJson();
        System.out.println(proJson);
        resp.getWriter().write(proJson);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
