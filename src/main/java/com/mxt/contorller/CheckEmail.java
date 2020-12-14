package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.ResultInfo;
import com.mxt.service.IUserService;
import com.mxt.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;

@WebServlet("/checkEmail")
public class CheckEmail extends javax.servlet.http.HttpServlet {
    private IUserService userService = new UserServiceImpl();
    ResultInfo resultInfo = new ResultInfo();
    ObjectMapper mapper = new ObjectMapper();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        try {
            //接收携带的验证码和code
            System.out.println("邮箱验证请求到达...");
            String code = request.getParameter("code");
            System.out.println("验证的code："+code);
            //查询唯一uuid 改状态为激活
            Integer i= userService.changeStatus(code);
            if (i>0){
                response.getWriter().write("验证完成，你可以登录了");
                response.getWriter().write("<a href="+"http://localhost:8088/travel/login.html"+">去登陆</a>");
            }
        }catch (Exception e){
            response.getWriter().write("验证失败，请重新验证");
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        this.doPost(request, response);
    }
}