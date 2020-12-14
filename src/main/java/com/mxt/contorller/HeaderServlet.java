package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.User;
import com.mxt.service.IUserService;
import com.mxt.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("头部用户请求到达...");
            User dbUser = (User) req.getSession().getAttribute("dbUser");
            System.out.println("session中user"+dbUser.toString());
            System.out.println("mapperUser"+mapper.writeValueAsString(dbUser));
            if (dbUser != null) {
                //传值回去
                resp.getWriter().write(mapper.writeValueAsString(dbUser));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);


    }
}
