package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.User;
import com.mxt.service.IUserService;
import com.mxt.service.impl.UserServiceImpl;
import com.mxt.utils.MailUtils;
import com.mxt.utils.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activation")
public class ActivationServlet extends HeaderServlet{
    private IUserService userService =new UserServiceImpl();
    ObjectMapper mapper =new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            //密码转加密
            String Md5Password = Md5Util.encodeByMd5(password);
            String email = req.getParameter("email");
            User user = this.userService.checkUserByNameAndPwd(username, Md5Password);
            if (user!=null){
                //发送邮件
                MailUtils.sendMail(email,"您正在使用邮箱注册xx旅游网，请点击下面链接完成验证</br>"
                        +"<a href="+"http://localhost:8088/travel/checkEmail?code="+user.getCode()+">点击验证</a>","验证邮件");

                //通知用户
                resp.getWriter().write(mapper.writeValueAsString("请求已发送，请前往邮箱激活"));
            }else {
                //没有这个用户
                resp.getWriter().write(mapper.writeValueAsString("账号或密码不正确"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
