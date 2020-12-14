package com.mxt.contorller;


import cn.hutool.db.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.Pages;
import com.mxt.pojo.Route;
import com.mxt.service.IRouteService;
import com.mxt.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    // 创建一个service层的类对象
    private IRouteService routeService = new RouteServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    //根据id获得路线
    public void getRouteByCid(HttpServletRequest request, HttpServletResponse response) {
        try {
            Pages pages = new Pages();
            //当前模块
            String cid = request.getParameter("cid");
            //查询值
            String selectName = request.getParameter("selectName");
            //当前页 必传参
            Integer start = Integer.valueOf(request.getParameter("start"));
            //取当前页
            Integer currentPage = Integer.valueOf(request.getParameter("start"));
            //分页查询
            List<Route> routeList = routeService.getRouteByCid(cid, selectName, start);
            //根据条件查询总记录数
            int count = routeService.getCountById(cid, selectName);
            pages.setCount(count);
            pages.setPagesNumber((int) Math.ceil((double) count / (double) 5));
            pages.setData(routeList);
            pages.setCurrentPage(currentPage);
            //传回去
            response.getWriter().write(mapper.writeValueAsString(pages));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
