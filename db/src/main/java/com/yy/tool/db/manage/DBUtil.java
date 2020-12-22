package com.yy.tool.db.manage;

import com.yy.tool.db.entity.Connector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/12/22
 */

@Slf4j
public class DBUtil {


    /**
     * 开启连接
     * @param url
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(String url, String username, String password) throws SQLException {
//        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 关闭
     * @param conn
     */
    public static void close(Connection conn) {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("关闭数据库出错",e);
            }
        }
    }

    /**
     * 判断数据库是否可连接
     * @param connector
     * @return
     */
    public static boolean alive(Connector connector){
        Connection connection = null;
        try {
            connection =  getConnection(connector.getUrl(), connector.getUsername(),connector.getPassword());
        } catch (Exception e) {
            log.error("Failed to connect:{}", connector.getUrl(), e.getMessage());
        } finally {
            close(connection);
        }
        return null != connection;
    }


    /**
     * 获取表信息
     * @param connector
     * @return
     */
    public static List<String> getTable(Connector connector) {

        return null;
    }

}
