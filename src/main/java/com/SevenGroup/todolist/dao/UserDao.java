package com.SevenGroup.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SevenGroup.todolist.model.User;
import com.SevenGroup.todolist.utils.DBUtil;

public class UserDao {
	public List<User> getAllUsers() {
	    String sql = "SELECT * FROM users";
	    List<User> userList = new ArrayList<>();

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setUsername(rs.getString("username"));
	            user.setPassword(rs.getString("password")); // 可选
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setAvatar(rs.getString("avatar"));
	            user.setRole(rs.getString("role"));
	            userList.add(user);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userList;
	}

	public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;

       
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    
                    user = new User();
                    
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setRole(rs.getString("role"));
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
	public static void updateProfile(User user) throws SQLException {
		String sql = "UPDATE users SET name = ?";
		List<Object> params = new ArrayList<>();
		params.add(user.getName());

		if (user.getEmail() != null && !user.getEmail().isEmpty()) {
		    sql += ", email = ?";
		    params.add(user.getEmail());
		}
		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
		    sql += ", password = ?";
		    params.add(user.getPassword());
		}
		if (user.getAvatar() != null && !user.getAvatar().isEmpty()) {
		    sql += ", avatar = ?";
		    params.add(user.getAvatar());
		}

		sql += " WHERE id = ?";
		params.add(user.getId());
	    
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	    	for (int i = 0; i < params.size(); i++) {
	            ps.setObject(i + 1, params.get(i)); // 逐个填充占位符 ?
	        }
	    	

	        ps.executeUpdate();
	    }
	    
	}

}
