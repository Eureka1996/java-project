package com.wufuqiang.lombok;

import com.wufuqiang.jdbc.JDBCUtil;

/**
 * @author: Wu Fuqiang
 * @create: 2021-06-24 21:15
 */
public class BuilderDemo {
    public static void main(String[] args) {

        JDBCUtil jdbcUtil = JDBCUtil.builder().build();
        System.out.println(jdbcUtil.getHost());


    }
}
