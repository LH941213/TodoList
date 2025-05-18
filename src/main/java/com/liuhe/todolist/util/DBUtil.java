package com.liuhe.todolist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBUtil {
	private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=TodoDB;encrypt=true;trustServerCertificate=true";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "Liuhe1213";
    static {
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }
	
	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
    }
	

}
