package com.mxt.contorller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remember")
public class RememberServlet extends HeaderServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求
        String flag = req.getParameter("flag");
        if (flag.equals("y")){
            //自动登录 设置标志
            req.getSession().setAttribute("remember",flag);
            System.out.println(flag);
        }else {
            req.getSession().setAttribute("remember",flag);
            System.out.println(flag);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
