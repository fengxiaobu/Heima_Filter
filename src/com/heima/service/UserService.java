package com.heima.service;

import com.heima.bean.User;
import com.heima.dao.UserDao;

import java.sql.SQLException;

/**
 * Created by Feng on 2017/1/7.
 */
public class UserService {
    public static User login(User user) throws SQLException {
       return UserDao.login(user);
    }

    public static User findUserByUsername(String username) throws SQLException {
        return UserDao.findUserByUsername(username);
    }

    public static int register(User user) throws SQLException {
        return  UserDao.register(user);
    }
}
