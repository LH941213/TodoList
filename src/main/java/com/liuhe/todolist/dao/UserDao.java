package com.liuhe.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.liuhe.todolist.model.User;
import com.liuhe.todolist.util.DBUtil;

public class UserDao {
	public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;

        // 使用 try-with-resources 自动管理连接和其它资源
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // 假设 User 的构造函数是：User(int id, String username, String password)
                    user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        return user;
    }
	public boolean insertUser(String username, String password, String email) {
	    String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
	    
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, username);
	        stmt.setString(2, password);  // **建议这里先加密密码**
	        stmt.setString(3, email);
	        
	        return stmt.executeUpdate() > 0;  // 插入成功返回 true
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
