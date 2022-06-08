package com.java.roadstudent.roadjava.util;
import java.sql.*;

public class DBUtil {
    private static final String URL ="jdbc:mysql://47.113.218.215:8888/student";
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME ="root";
    private static final String PWD ="123456";

    static {
        try {
            //com.mysql.jdbc.Driver 静态代码块
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConn(){
        try {
           return  DriverManager.getConnection(URL,USER_NAME,PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //获取数据库链接
    public static void closeConn(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void closePs(PreparedStatement ps){

        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRs(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
