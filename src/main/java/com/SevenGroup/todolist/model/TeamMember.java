package com.SevenGroup.todolist.model;

import java.util.Date;

public class TeamMember {
	private int teamId;
	private int userId;
	private String roleInTeam;
	private Date joinedAt=new Date();
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRoleInTeam() {
		return roleInTeam;
	}
	public void setRoleInTeam(String roleInTeam) {
		this.roleInTeam = roleInTeam;
	}
	public Date getJoinedAt() {
		return joinedAt;
	}
	public void setJoinedAt(Date joinedAt) {
		this.joinedAt = joinedAt;
	}
	
}
