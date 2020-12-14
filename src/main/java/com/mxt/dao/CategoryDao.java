package com.mxt.dao;
//分类
import com.mxt.pojo.Category;
import com.mxt.pojo.Route;

import java.util.List;

public interface CategoryDao {
    List<Category> getCategoryName();

    List<Route> getRouteListByCategoryCid(int parseInt);
}
