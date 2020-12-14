package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.ResultInfo;
import com.mxt.pojo.User;
import com.mxt.service.IUserService;
import com.mxt.service.impl.UserServiceImpl;
import com.mxt.utils.MailUtils;
import com.mxt.utils.Md5Util;
import com.mxt.utils.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends javax.servlet.http.HttpServlet {
    private IUserService userService = new UserServiceImpl();
    ResultInfo resultInfo = new ResultInfo();
    ObjectMapper mapper = new ObjectMapper();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        try {
            //接收请求
            Map<String, String[]> parameterMap = request.getParameterMap();
            //封装
            User user = new User();
            BeanUtils.populate(user, parameterMap);
            System.out.println("注册的用户是：" + user.toString());
            String check = request.getParameter("check");
            //验证验证码
            String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
            System.out.println("输入的验证码："+check);
            System.out.println("生成的验证码是:"+checkcode_server);
            if (checkcode_server.equalsIgnoreCase(check)) {
                //注册
                //生成唯一uuid
                user.setCode(UuidUtil.getUuid());
                System.out.println("设置给user的uuid："+user.getCode());
                //加密密码
                String md5 = Md5Util.encodeByMd5(user.getPassword());
                user.setPassword(md5);
                this.userService.registUser(user);
                resultInfo.setFlag(true);
                response.getWriter().write(mapper.writeValueAsString(resultInfo));
                //发送邮件
                MailUtils.sendMail(user.getEmail(),"您正在使用邮箱注册xx旅游网，请点击下面链接完成验证</br>"
                        +"<a href="+"http://192.168.4.6:8080/travel/checkEmail?code="+user.getCode()+">点击验证</a>","验证邮件");
            } else {
                //验证码错误
                resultInfo.setFlag(false);
                response.getWriter().write(mapper.writeValueAsString(resultInfo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        this.doPost(request, response);
    }
}