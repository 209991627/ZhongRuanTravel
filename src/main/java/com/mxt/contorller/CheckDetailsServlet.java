package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.Category;
import com.mxt.pojo.Route;
import com.mxt.pojo.RouteImg;
import com.mxt.pojo.Seller;
import com.mxt.service.IRouteService;
import com.mxt.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/checkDetails")
public class CheckDetailsServlet extends HeaderServlet {
    private IRouteService routeService = new RouteServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String rid = req.getParameter("rid");
            Route route = this.routeService.getRouteByRid(rid);
            //获取所属商家
            Seller seller = this.routeService.getSellerBySid(route.getSid());
            route.setSeller(seller);
            //获取所属分类
            Category category =this.routeService.getCategoryByCid(route.getCid());
            route.setCategory(category);
            //商品详情图片列表
            List<RouteImg> routeImgList = this.routeService.getDetailsByRid(rid);
            route.setRouteImgList(routeImgList);
            resp.getWriter().write(mapper.writeValueAsString(route));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
