package com.wufuqiang.jdbc;


import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@Slf4j
public class JDBCUtil {

    @Builder.Default
    private String host="localhost";
    @Builder.Default
    private int port=3306;
    private String db;
    private String username;
    private String password;
    private String driver;
    private Connection connection;

    public static void main(String[] args) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.builder()
//                .host("rm-2ze2z9r964459604ero.mysql.rds.aliyuncs.com")
//                .host("rm-2ze2z9r964459604ero.mysql.rds.aliyuncs.com") //线上
                .host("rm-2ze2z9r964459604ero.mysql.rds.aliyuncs.com") //测试
                .port(3306)
//                .username("data_sync_rw") //线上
                .username("data_sync_rw") //测试
//                .password("xUSq5QOzrSVKIheAMK2e") //线上
                .password("xUSq5QOzrSVKIheAMK2e") //测试
                .db("airflow_data_sync_prod")
                .build().connect();
        jdbcUtil.getLastestStateByDagId("airflow_data_sync_prod","dag_run","wx1v1-b10HxMNG-offline");
        log.info("-------------------------");
        jdbcUtil.getLastestStateByDagId("airflow_data_sync_prod","dag_run","wx-GrYdFhkY-offline");
        jdbcUtil.close();

        System.out.println("running".equals(null));
    }


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

    public void getLastestStateByDagId(String db,String table,String dagId) throws SQLException {
        if(this.connection == null){
            log.error("数据库连接为null。");
            return ;
        }
        String sql = String.format("select state from %s.%s where dag_id = ? " +
                "and execution_date = " +
                "(select max(execution_date) from %s.%s where dag_id = ?)",db,table,db,table);
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        pstmt.setString(1,dagId);
        pstmt.setString(2,dagId);

        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            String state = rs.getString(1);
            log.info("{}的状态为{}",dagId,state);
        }
    }

    public void getLastestStateByTaskId(String db,String table,String dagId,String taskid) throws SQLException {
        if(this.connection == null){
            log.error("数据库连接为null。");
            return ;
        }
        String sql = String.format("select state from %s.%s where dagId = ? " +
                "and execution_date = " +
                "(select max(execution_date) from %s.%s where dag_id = ?)",db,table,db,table);
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        pstmt.setString(1,dagId);
        pstmt.setString(2,dagId);

        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            String state = rs.getString(1);
            log.info("{}的状态为{}",dagId,state);
        }
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
