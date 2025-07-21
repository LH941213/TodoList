package com.SevenGroup.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SevenGroup.todolist.utils.DBUtil;

public class TeamMemberDao {
	

    // 添加多个成员进团队（含角色）
    public void addMembersToTeam(int teamId, List<Integer> memberIds, String defaultRole) throws SQLException {
        String sql = "INSERT INTO team_members (team_id, user_id, role_in_team) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int userId : memberIds) {
                ps.setInt(1, teamId);
                ps.setInt(2, userId);
                ps.setString(3, defaultRole);
                ps.executeUpdate();
            }
        }
    }

    // 获取某团队的所有成员 ID（或者扩展成返回完整 User 对象）
    public List<Integer> getMembersByTeam(int teamId) throws SQLException {
        List<Integer> userIds = new ArrayList<>();
        String sql = "SELECT user_id FROM team_members WHERE team_id = ?";
        try (Connection conn = DBUtil.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userIds.add(rs.getInt("user_id"));
            }
        }
        return userIds;
    }

}
