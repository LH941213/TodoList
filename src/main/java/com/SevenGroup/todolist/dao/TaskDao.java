package com.SevenGroup.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SevenGroup.todolist.model.Task;
import com.SevenGroup.todolist.utils.DBUtil;

public class TaskDao {
	public void addTask(Task task) throws SQLException{
		 String sql = "INSERT INTO Tasks (title, description, user_id, team_id) VALUES (?, ?, ?, ?)";
		    try (Connection conn = DBUtil.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {

		        stmt.setString(1, task.getTitle());
		        stmt.setString(2, task.getDescription());
		        stmt.setInt(3, task.getUserId());

		        if (task.getTeamId() != null) {
		            stmt.setInt(4, task.getTeamId());
		        } else {
		            stmt.setNull(4, java.sql.Types.INTEGER);
		        }

		        stmt.executeUpdate();
		    }
	}
	public List<Task>getTasksByUserId(int userId) throws SQLException{
		List<Task> tasks =new ArrayList<>();
		String sql = "SELECT t.id, t.title, t.description, t.is_completed, t.created_at, t.updated_at, " +
                "t.team_id, tm.team_name " +
                "FROM Tasks t " +
                "LEFT JOIN Teams tm ON t.team_id = tm.team_id " +
                "WHERE t.user_id = ?";

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
		                int teamId = rs.getInt("team_id");
		                if (!rs.wasNull()) {
		                    task.setTeamId(teamId);
		                }
		                task.setTeamName(rs.getString("team_name")); // ✨ 新增字段

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
	public List<Task> getTasksByTeamId(int teamId) {
	    List<Task> tasks = new ArrayList<>();

	    // JOIN users 表以获取负责人姓名
	    String sql = """
	        SELECT t.id, t.title, t.status, t.team_id, t.deadline,
	               t.assigned_to, u.name AS assignee_name
	        FROM tasks t
	        JOIN users u ON t.assigned_to = u.id
	        WHERE t.team_id = ?
	    """;

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, teamId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Task task = new Task();
	            task.setId(rs.getInt("id"));
	            task.setTitle(rs.getString("title"));
	            task.setStatus(rs.getString("status"));
	            task.setTeamId(rs.getInt("team_id"));
	            task.setDeadline(rs.getDate("deadline"));
	            task.setAssignedTo(rs.getInt("assigned_to"));
	            task.setAssigneeName(rs.getString("assignee_name")); // 👈 展示用字段

	            tasks.add(task);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tasks;
	}
}
