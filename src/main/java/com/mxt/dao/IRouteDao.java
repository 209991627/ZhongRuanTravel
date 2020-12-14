package com.mxt.dao;

import com.mxt.pojo.Category;
import com.mxt.pojo.Route;
import com.mxt.pojo.RouteImg;
import com.mxt.pojo.Seller;

import java.util.List;

public interface IRouteDao {
    List<Route> getRouteByCid(String cid,String selectName,Integer start);

    int getCountById(String cid, String selectName);

    List<RouteImg> getDetailsByRid(String rid);

    Route getRouteByRid(String rid);

    Seller getSellerBySid(int sid);

    Category getCategoryByCid(int cid);
}
