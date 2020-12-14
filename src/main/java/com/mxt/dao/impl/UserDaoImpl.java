package com.mxt.dao.impl;

import com.mxt.dao.IUserDao;
import com.mxt.pojo.Category;
import com.mxt.pojo.User;
import com.mxt.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User checkUserByName(String htmlUsername) {
        try{
            String sql="SELECT * FROM tab_user WHERE username =?";
            return this.template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),htmlUsername);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void registUser(User user) {
        try {
            String sql="INSERT INTO tab_user VALUES (NULL,?,?,?,?,?,?,?,'0',?);";
            template.update(sql,user.getUsername(),user.getPassword(),user.getName(),
                    user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),
                    user.getCode()
                    );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer changeStatus(String code) {
        try {
            String sql="UPDATE tab_user SET STATUS ='1' WHERE CODE= ? ";
            return template.update(sql,code);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public User checkUserByNameAndPwd(String htmlUsername, String htmlPassword) {
        try{
            String sql="SELECT * FROM tab_user WHERE username =? AND PASSWORD=?";
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),
                    htmlUsername,htmlPassword);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Category> getAllCategory() {
        try{
            String sql ="SELECT * FROM tab_category GROUP BY cid;";
            return template.query(sql,new BeanPropertyRowMapper<>(Category.class));
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User getUserByCode(String code) {
        try {
            String sql="SELECT * FROM tab_user WHERE CODE=?";
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),code);
        }catch (Exception e){
            return null;
        }
    }
}
