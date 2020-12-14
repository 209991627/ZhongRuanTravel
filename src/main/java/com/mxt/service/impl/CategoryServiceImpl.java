package com.mxt.service.impl;

import com.mxt.dao.CategoryDao;
import com.mxt.dao.impl.CategoryDaoImpl;
import com.mxt.pojo.Category;
import com.mxt.pojo.Route;
import com.mxt.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao =new CategoryDaoImpl();
    @Override
    public List<Category> getCategoryName() {
        return this.categoryDao.getCategoryName();
    }

    @Override
    public List<Route> getRouteListByCategoryCid(int parseInt) {
        return this.categoryDao.getRouteListByCategoryCid(parseInt);
    }

    @Override
    public List<Route> getRouteListByCategoryCid2(int parseInt) {
        return null;
    }

    @Override
    public List<Route> getRouteListByCategoryCid3(int parseInt) {
        return null;
    }

    @Override
    public Category getCategoryNameByCid(int parseInt) {
        return null;
    }
}
