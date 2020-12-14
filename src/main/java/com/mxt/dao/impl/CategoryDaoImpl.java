package com.mxt.dao.impl;

import com.mxt.dao.CategoryDao;
import com.mxt.pojo.Category;
import com.mxt.pojo.Route;
import com.mxt.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> getCategoryName() {
        try {
            String sql= "SELECT * FROM tab_category GROUP BY cid";
            return template.query(sql,new BeanPropertyRowMapper<>(Category.class));
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Route> getRouteListByCategoryCid(int parseInt) {
        try {
            String sql="SELECT * FROM tab_route WHERE cid=? ORDER BY ASC";
            return template.query(sql,new BeanPropertyRowMapper<>(Route.class),parseInt);
        }catch (Exception e){
            return null;
        }
    }
}
