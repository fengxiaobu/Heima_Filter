package com.heima.dao;

import com.heima.bean.User;
import com.heima.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by Feng on 2017/1/7.
 */
public class UserDao {
    public static User login(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDatasSource());
        String sql = "select * from user where username=? and password=?";
        Object object[] = {user.getUsername(), user.getPassword()};
        User userBean = runner.query(sql, new BeanHandler<User>(User.class), object);
        return userBean;
    }

    public static User findUserByUsername(String username) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDatasSource());
        String sql = "select username from user where username=?";
        User userBean = runner.query(sql, new BeanHandler<User>(User.class), username);
        return userBean;
    }

    public static int register(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDatasSource());
        String sql = "insert into user values (?,?,?,?,?,?,?,?)";
        Object object[] = {user.getId(), user.getUsername(), user.getPassword(), user.getNickname(), user.getType(), user.getSex(), user.getBirthday(), user.getEmail()};
        int i = runner.update(sql, object);
        return i;
    }
}
