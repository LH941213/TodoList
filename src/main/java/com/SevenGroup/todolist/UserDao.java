package com.SevenGroup.todolist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;

       
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    
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
	        stmt.setString(2, password);  
	        stmt.setString(3, email);
	        
	        return stmt.executeUpdate() > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
