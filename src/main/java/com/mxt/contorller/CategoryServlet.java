package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.Category;
import com.mxt.service.IUserService;
import com.mxt.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
   private IUserService userService =new UserServiceImpl();
   ObjectMapper mapper =new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求
        List<Category> categorys= this.userService.getAllCategory();
       /* categorys.sort((o1, o2) ->  o1.getCid()-o2.getCid());*/
        System.out.println(categorys);

        resp.getWriter().write(mapper.writeValueAsString(categorys));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
