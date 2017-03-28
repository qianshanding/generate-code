package com.qianshanding.holdall.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接类
 *
 * @author fish
 */
public class ConnectionFactory {
    private static Connection conn;

    private ConnectionFactory() {
        super();
    }

    public static Connection getConnection(String driverClassName, String url, String userName,
                                           String passWord) throws Exception {
        if (conn == null) {
            try {
                Class.forName(driverClassName);
                try {
                    conn = DriverManager.getConnection(url, userName, passWord);
                } catch (SQLException e) {
                    throw new Exception(e.getMessage(), e);
                }
            } catch (ClassNotFoundException e) {
                throw new Exception(e.getMessage(), e);
            }
        }
        return conn;
    }
}
