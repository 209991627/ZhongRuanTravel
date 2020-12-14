package com.mxt.contorller;
//注销页
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cancellation")
public class CancellationServlet extends HeaderServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //清除Session
        System.out.println("注销请求到达...");
        //清除session
        req.getSession().removeAttribute("dbUser");
        //清除cookie
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginCookie")){
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
        resp.sendRedirect(req.getContextPath()+"/login.html");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
