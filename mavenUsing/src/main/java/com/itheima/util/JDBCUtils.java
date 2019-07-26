package com.itheima.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;
    static {
        try {
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties p = new Properties();
            p.load(in);
            ds = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDs(){
        return  ds;
    }
    public static Connection getConneciton() throws SQLException {
        return ds.getConnection();
    }
}
