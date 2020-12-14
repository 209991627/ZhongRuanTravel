package com.mxt.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxt.pojo.Category;
import com.mxt.pojo.Route;
import com.mxt.service.CategoryService;
import com.mxt.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/category1")
public class CategoryIndexServlet extends BaseServlet{
    private CategoryService categoryService = new CategoryServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    //    获取排名前三的分类
    public void getCategoryName(HttpServletRequest request, HttpServletResponse response) {
//        调用服务类  获得排名前三的分类
        List<Category> categoryList = categoryService.getCategoryName();
        try {
//        将数据序列化为json格式
            String str = mapper.writeValueAsString(categoryList);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    指定分类id获取旅游线路的数据 cid为1 要4条数据
    public void getRouteListByCategoryCid(HttpServletRequest request,HttpServletResponse response){
//        获取分类的cid
        String cid = request.getParameter("cid");
//        调用服务类 获取旅游线路数据
        List<Route> routeList = categoryService.getRouteListByCategoryCid(Integer.parseInt(cid));
//        将数据序列化成json
        try {
            String str = mapper.writeValueAsString(routeList);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    指定分类id获取旅游线路的数据 cid为2 要6条数据
    public void getRouteListByCategoryCid2(HttpServletRequest request,HttpServletResponse response){
//        获取分类的cid
        String cid = request.getParameter("cid");
//        调用服务类 获取旅游线路数据
        List<Route> routeList = categoryService.getRouteListByCategoryCid2(Integer.parseInt(cid));
//        将数据序列化成json
        try {
            String str = mapper.writeValueAsString(routeList);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    指定分类id获取旅游线路的数据 cid为3 要6条数据
    public void getRouteListByCategoryCid3(HttpServletRequest request,HttpServletResponse response){
//        获取分类的cid
        String cid = request.getParameter("cid");
//        调用服务类 获取旅游线路数据
        List<Route> routeList = categoryService.getRouteListByCategoryCid3(Integer.parseInt(cid));
//        将数据序列化成json
        try {
            String str = mapper.writeValueAsString(routeList);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getCategoryNameByCid(HttpServletRequest request,HttpServletResponse response){
        //        获取分类的cid
        String cid = request.getParameter("cid");
//        调用服务类方法 获得分类数据
        Category category = categoryService.getCategoryNameByCid(Integer.parseInt(cid));
        //        将数据序列化成json
        try {
            String str = mapper.writeValueAsString(category);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
