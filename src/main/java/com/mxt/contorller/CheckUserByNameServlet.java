package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.ResultInfo;
import com.mxt.pojo.User;
import com.mxt.service.IUserService;
import com.mxt.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;

@WebServlet("/checkUserByName")
public class CheckUserByNameServlet extends javax.servlet.http.HttpServlet {
    private IUserService userService =new UserServiceImpl();
    private ObjectMapper mapper =new ObjectMapper();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        System.out.println("验证请求到达...");
        try {
            String htmlUsername = request.getParameter("username");
            System.out.println("注册名字为："+htmlUsername);
            //查数据库
            System.out.println(11);
            User user= this.userService.checkUserByName(htmlUsername);
            System.out.println(22);
            ResultInfo resultInfo =new ResultInfo();

            //判断
            if (user==null){
                //可以注册
                resultInfo.setFlag(true);
                response.getWriter().write(mapper.writeValueAsString(resultInfo));
                System.out.println(1);
            }else {
                //账号已存在
                resultInfo.setFlag(false);
                response.getWriter().write(mapper.writeValueAsString(resultInfo));
                System.out.println(2);
            }
        }catch (Exception e){
            response.getWriter().write("false");
            System.out.println(3);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        this.doPost(request, response);
    }
}