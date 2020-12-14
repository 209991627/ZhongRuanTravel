package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.User;
import com.mxt.service.IUserService;
import com.mxt.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieServlet")
public class CookieServlet extends HeaderServlet {
    private IUserService userService = new UserServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询cookie是否存
        System.out.println("cookieServlet请求...");
        Cookie[] cookies = req.getCookies();
        //cookie标志
        boolean flag=false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginCookie")) {
                    String code = cookie.getValue();
                    System.out.println("有cookie："+code);
                    //去数据库查code
                    User codeUser = this.userService.getUserByCode(code);
                    if (codeUser != null) {
                        flag=true;
                        //传值回去
                        resp.getWriter().write(mapper.writeValueAsString(codeUser));
                        break;
                    }
                }
            }
            //判断
            if (flag==false){
                resp.getWriter().write(mapper.writeValueAsString("nocookie"));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
