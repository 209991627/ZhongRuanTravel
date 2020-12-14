package com.mxt.service.impl;

import com.mxt.dao.IRouteDao;
import com.mxt.dao.impl.RouteDaoImpl;
import com.mxt.pojo.Category;
import com.mxt.pojo.Route;
import com.mxt.pojo.RouteImg;
import com.mxt.pojo.Seller;
import com.mxt.service.IRouteService;

import java.util.List;

public class RouteServiceImpl implements IRouteService {
    private IRouteDao routeDao =new RouteDaoImpl();
    @Override
    public List<Route> getRouteByCid(String cid,String selectName,Integer start) {
        return this.routeDao.getRouteByCid(cid,selectName,start);
    }

    @Override
    public int getCountById(String cid, String selectName) {
        return this.routeDao.getCountById(cid,selectName);
    }

    @Override
    public List<RouteImg> getDetailsByRid(String rid) {
        return this.routeDao.getDetailsByRid(rid);
    }

    @Override
    public Route getRouteByRid(String rid) {
        return this.routeDao.getRouteByRid(rid);
    }

    @Override
    public Seller getSellerBySid(int sid) {
        return this.routeDao.getSellerBySid(sid);
    }

    @Override
    public Category getCategoryByCid(int cid) {
        return this.routeDao.getCategoryByCid(cid);
    }
}
