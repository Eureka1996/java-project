package com.wufuqiang.jdbc;


import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@Slf4j
public class JDBCUtil {

    private String host;
    private int port;
    private String db;
    private String username;
    private String password;
    private String driver;
    private Connection connection;


    public JDBCUtil connect(){
        driver = driver == null?"com.mysql.cj.jdbc.Driver":driver;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("加载Mysql Driver失败。driver:"+driver);
            e.printStackTrace();
        }
        String jdbcUrl = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                this.host,this.port,this.db);
        log.warn("jdbcUrl:"+jdbcUrl);
        try {
            this.connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
        } catch (SQLException throwables) {
            log.error("获取Mysql connection失败。url:"+jdbcUrl);
            throwables.printStackTrace();
        }
        return this;
    }



    public void close(){
        if(this.connection != null){
            try {
                this.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
