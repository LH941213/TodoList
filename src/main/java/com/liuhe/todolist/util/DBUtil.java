package com.liuhe.todolist.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBUtil {
	private static DataSource dataSource;
	static {
		try {
			// 初始化JNDI上下文
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
    // 查找数据源
			dataSource = (DataSource) envContext.lookup("jdbc/TodoDBPool");
		} catch (NamingException  e) {
			// TODO 自動生成された catch ブロック
			throw new RuntimeException("Failed to initialize database connection pool", e);
		}
        
	}
	 /**
     * 从连接池获取数据库连接
     * @return 数据库连接对象
     * @throws SQLException 如果获取连接失败
     */
	public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
	/**
     * 关闭数据库连接 (实际上是归还到连接池)
     * @param conn 要关闭的连接
     */
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            	// 记录日志或处理异常
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }
}
