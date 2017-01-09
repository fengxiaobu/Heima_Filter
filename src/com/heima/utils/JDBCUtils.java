package com.heima.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0获取连接信息
 *
 * @author Feng
 */
public class JDBCUtils {
    private static final ComboPooledDataSource CPDS = new ComboPooledDataSource();

    /**
     * 获取Connection连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = CPDS.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获取连接池
     *
     * @return
     */
    public static DataSource getDatasSource() {
        return CPDS;
    }
}
