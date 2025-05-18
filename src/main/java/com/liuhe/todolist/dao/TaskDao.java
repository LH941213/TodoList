package com.liuhe.todolist.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuhe.todolist.model.Task;
import com.liuhe.todolist.util.DBUtil;
public class TaskDao {
	public void addTask(Task task) throws SQLException{
		String sql="INSERT INTO Tasks (title, description,user_id) VALUES (?,?,?)";
		try(Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, task.getTitle());
				stmt.setString(2, task.getDescription());
				stmt.setInt(3, task.getUserId());
				stmt.executeUpdate();
		}
	}
	public List<Task>getTasksByUserId(int userId) throws SQLException{
		List<Task> tasks =new ArrayList<>();
		String sql = "SELECT id, title, description, is_completed, created_at, updated_at FROM Tasks WHERE user_id=?";
		try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setInt(1,userId );
	             try(ResultSet rs = stmt.executeQuery()) {
		            while (rs.next()) {
		                Task task = new Task();
		                task.setId(rs.getInt("id"));
		                task.setTitle(rs.getString("title"));
		                task.setDescription(rs.getString("description"));
		                task.setCompleted(rs.getBoolean("is_completed"));
		                task.setCreatedAt(rs.getTimestamp("created_at"));
		                task.setUpdatedAt(rs.getTimestamp("updated_at")); 
		                tasks.add(task);
	            }
	        }
		}
		return tasks;
	}
	public Task getTaskById(int id,int userId) throws SQLException {
        Task task = null;
        String sql = "SELECT id, title, description, is_completed, created_at, updated_at FROM tasks WHERE id = ? AND user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2,userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	task = new Task();
 	                task.setId(rs.getInt("id"));
 	                task.setTitle(rs.getString("title"));
 	                task.setDescription(rs.getString("description"));
 	                task.setCompleted(rs.getBoolean("is_completed"));
 	               task.setCreatedAt(rs.getTimestamp("created_at")); // 使用 getTimestamp
                   task.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        }
        return task;
    }
	public void updateTask(Task task,int userId) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, description = ?, is_completed=?,updated_at = CURRENT_TIMESTAMP WHERE id = ? AND user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setBoolean(3, task.isCompleted());
            pstmt.setInt(4, task.getId());
            pstmt.setInt(5, userId);
            pstmt.executeUpdate();
        }
    }
	public void deleteTask(int id,int userId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ? AND user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        }
    }
}
