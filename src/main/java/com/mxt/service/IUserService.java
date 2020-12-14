package com.mxt.service;

import com.mxt.pojo.Category;
import com.mxt.pojo.User;

import java.util.List;

public interface IUserService {
    User checkUserByName(String htmlUsername);

    void registUser(User user);

    Integer changeStatus(String code);

    User checkUserByNameAndPwd(String htmlUsername, String htmlPassword);

    List<Category> getAllCategory();

    User getUserByCode(String code);

    //注册用户

}
