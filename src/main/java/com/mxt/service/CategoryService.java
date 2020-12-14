package com.mxt.service;

import com.mxt.pojo.Category;
import com.mxt.pojo.Route;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryName();

    List<Route> getRouteListByCategoryCid(int parseInt);

    List<Route> getRouteListByCategoryCid2(int parseInt);

    List<Route> getRouteListByCategoryCid3(int parseInt);

    Category getCategoryNameByCid(int parseInt);
}
