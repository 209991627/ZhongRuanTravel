package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.ResultInfo;
import com.mxt.pojo.User;
import com.mxt.service.IUserService;
import com.mxt.service.impl.UserServiceImpl;
import com.mxt.utils.Md5Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

@WebServlet("/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    private IUserService userService = new UserServiceImpl();
    ResultInfo resultInfo =new ResultInfo();
    ObjectMapper mapper =new ObjectMapper();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        try {
            System.out.println("登录请求到达...");
            //接收请求
            String htmlUsername = request.getParameter("username");
            String htmlPassword = request.getParameter("password");
            //转为加密密码
            String Md5Password = Md5Util.encodeByMd5(htmlPassword);
            String check = request.getParameter("check");

            //先判断验证码
            String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
            if (checkcode_server.equalsIgnoreCase(check)) {
                User dbUser = userService.checkUserByNameAndPwd(htmlUsername,Md5Password);
                if (dbUser!=null){
                    if (Integer.parseInt(dbUser.getStatus())==1){
                        resultInfo.setData(dbUser);
                        resultInfo.setFlag(true);
                        resultInfo.setErrorMsg("登录成功");
                        request.getSession().setAttribute("resultInfo",resultInfo);
                        request.getSession().setAttribute("dbUser",dbUser);
                        response.getWriter().write(mapper.writeValueAsString(resultInfo));


                        //判断用户是否勾选自动登录
                        String flag = (String) request.getSession().getAttribute("remember");
                        //设置自动登录Cookie
                        Cookie cookie = new Cookie("loginCookie",dbUser.getCode());
                        if (flag.equals("y")){
                            cookie.setMaxAge(60*60*24);
                            response.addCookie(cookie);
                        }else {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }

                    }else {
                        //没激活
                        resultInfo.setFlag(false);
                        resultInfo.setErrorMsg("该用户尚未激活，请先激活再登陆");
                        response.getWriter().write(mapper.writeValueAsString(resultInfo));
                    }
                }else {
                    //账号密码错
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMsg("账号或密码输入不正确");
                    response.getWriter().write(mapper.writeValueAsString(resultInfo));
                }
            }else {
                //验证码错
                resultInfo.setErrorMsg("验证码输入错误");
                response.getWriter().write(mapper.writeValueAsString(resultInfo));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        this.doPost(request, response);
    }
}