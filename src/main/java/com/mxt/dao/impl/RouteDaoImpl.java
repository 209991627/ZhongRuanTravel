package com.mxt.dao.impl;

import com.mxt.dao.IRouteDao;
import com.mxt.pojo.Category;
import com.mxt.pojo.Route;
import com.mxt.pojo.RouteImg;
import com.mxt.pojo.Seller;
import com.mxt.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements IRouteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Route> getRouteByCid(String cid, String selectName, Integer start) {
        /*SELECT * FROM tab_route WHERE cid ='5' AND rname LIKE '%春节%' LIMIT 0,5 ;*/
        try {
            start = (start - 1) * 5;
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM tab_route WHERE 1=1");
            sb.append(" AND cid = " + cid);
            if (selectName != null && !selectName.equals("") && !selectName.equals("null")) {
                sb.append(" AND rname LIKE '%" + selectName + "%' ");
            };
            sb.append(" GROUP BY price ");
            sb.append(" LIMIT ").append(start).append(",5");
            System.out.println("查询语句是：" + sb.toString());
            return template.query(sb.toString(), new BeanPropertyRowMapper<>(Route.class));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getCountById(String cid, String selectName) {
        /*SELECT COUNT(*) FROM tab_route WHERE  cid ='1' AND rname LIKE '%春节%';*/
        try {
            StringBuilder sb =new StringBuilder();
            sb.append("SELECT COUNT(*) FROM tab_route WHERE 1=1 ");
            if (cid!=null){
                sb.append(" AND cid = "+cid);
            }
            if (selectName != null && !selectName.equals("") && !selectName.equals("null")) {
                sb.append(" AND rname LIKE '%" + selectName + "%' ");
            };
            return template.queryForObject(sb.toString(), Integer.class);
        }catch (Exception e){
            return 0;
        }

    }

    @Override
    public List<RouteImg> getDetailsByRid(String rid) {
        /*SELECT * FROM tab_route_img WHERE rid='15';*/
        try {
            String sql="SELECT * FROM tab_route_img WHERE rid=?";
            return template.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Route getRouteByRid(String rid) {
        try{
            String sql="SELECT * FROM tab_route WHERE rid=?";
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(Route.class),rid);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Seller getSellerBySid(int sid) {
        try {
            String sql="SELECT * FROM tab_seller WHERE sid =?";
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(Seller.class),sid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Category getCategoryByCid(int cid) {
        try {
            String sql="SELECT * FROM tab_category WHERE cid =?";
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(Category.class),cid);
        }catch (Exception e){
            return null;
        }

    }
}
