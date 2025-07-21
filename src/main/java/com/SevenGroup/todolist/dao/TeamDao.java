package com.SevenGroup.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.SevenGroup.todolist.model.Team;
import com.SevenGroup.todolist.utils.DBUtil;

public class TeamDao {
	

	    // 创建团队并返回生成的 team_id
	    public int createTeam(String teamName, String description, int creatorId) throws SQLException {
	        String sql = "INSERT INTO teams (team_name, description, created_by) VALUES (?, ?, ?)";
	        try (Connection conn = DBUtil.getConnection();
	        		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            ps.setString(1, teamName);
	            ps.setString(2, description);
	            ps.setInt(3, creatorId);
	            ps.executeUpdate();

	            ResultSet rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                return rs.getInt(1); // 返回新创建的 team_id
	            }
	        }
	        return -1;
	    }

	    // 获取某个用户所属的所有团队
	    public List<Integer> getTeamIdsByUser(int userId) throws SQLException {
	        String sql = "SELECT team_id FROM team_members WHERE user_id = ?";
	        List<Integer> teamIds = new ArrayList<>();
	        try (Connection conn = DBUtil.getConnection();
	        		PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                teamIds.add(rs.getInt("team_id"));
	            }
	        }
	        return teamIds;
	    }

	    
	    public Team getTeamById(int teamId) throws SQLException {
	    	String sql = "SELECT t.team_id, t.team_name, t.description, t.created_by, t.created_at, u.name AS creator_name " +
	                "FROM teams t " +
	                "JOIN users u ON t.created_by = u.id " +
	                "WHERE t.team_id = ?";


	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, teamId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                Team team = new Team();
	                team.setTeamId(rs.getInt("team_id"));
	                team.setTeamName(rs.getString("team_name"));
	                team.setDescription(rs.getString("description"));
	                team.setCreatedBy(rs.getInt("created_by"));
	                team.setCreatedAt(rs.getTimestamp("created_at")); // 若为 DATETIME 类型
	                team.setCreatorName(rs.getString("creator_name")); // 新加字段
	                return team;
	            }
	        }

	        return null; // 找不到该团队
	    }
	    public List<Team> getTeamsByUser(int userId) throws SQLException {
	        List<Team> teams = new ArrayList<>();
	        String sql = "SELECT t.team_id, t.team_name " +
	                     "FROM teams t " +
	                     "JOIN team_members tm ON t.team_id = tm.team_id " +
	                     "WHERE tm.user_id = ?";

	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Team team = new Team();
	                team.setTeamId(rs.getInt("team_id"));
	                team.setTeamName(rs.getString("team_name"));
	                teams.add(team);
	            }
	        }
	        return teams;
	    }


}
