package com.liuhe.todolist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
	private static final String URL ="jdbc:sqlserver://localhost:1433;databaseName=TodoDB;encrypt=true;trustServerCertificate=true";
	private static final String USER = "sa";
    private static final String PASSWORD = "Liuhe1213";
    public static Connection getConnection() throws SQLException{
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 替换为你的数据库驱动类名
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Failed to load JDBC driver.", e);
        }
    	
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // 记录关闭连接失败的日志
            }
        }
    }
}
