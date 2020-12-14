package com.mxt.service.impl;

import com.mxt.dao.IUserDao;
import com.mxt.dao.impl.UserDaoImpl;
import com.mxt.pojo.Category;
import com.mxt.pojo.User;
import com.mxt.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao =new UserDaoImpl();

    @Override
    public User checkUserByName(String htmlUsername) {
        return this.userDao.checkUserByName(htmlUsername);
    }

    @Override
    public void registUser(User user) {
        this.userDao.registUser(user);
    }

    @Override
    public Integer changeStatus(String code) {
        return this.userDao.changeStatus(code);
    }

    @Override
    public User checkUserByNameAndPwd(String htmlUsername, String htmlPassword) {
        return this.userDao.checkUserByNameAndPwd(htmlUsername,htmlPassword);
    }

    @Override
    public List<Category> getAllCategory() {
        return this.userDao.getAllCategory();
    }

    @Override
    public User getUserByCode(String code) {
        return this.userDao.getUserByCode(code);
    }
}
