package com.mxt.dao;

import com.mxt.pojo.Category;
import com.mxt.pojo.User;

import java.util.List;

public interface IUserDao {
    User checkUserByName(String htmlUsername);

    void registUser(User user);

    Integer changeStatus(String code);

    User checkUserByNameAndPwd(String htmlUsername, String htmlPassword);

    List<Category> getAllCategory();

    User getUserByCode(String code);
}
