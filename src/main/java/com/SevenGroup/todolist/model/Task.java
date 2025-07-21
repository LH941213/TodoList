package com.SevenGroup.todolist.model;
import java.util.Date;
public class Task {
	private int id;
	private String title;
	private String description;
    private boolean isCompleted =false;
    private Date createdAt =new Date();
    private Date updatedAt; 
    private int userId; 
    private int assignedTo;    // 被分配者（如果有）
    private String assigneeName;

    private Integer teamId;    // 所屬團隊，可為 null
    private String teamName;

    private String status = "未着手"; // 例：未着手 / 進行中 / 完了
    private Date deadline;

    public Task() {}
    
    public Task(String title, String description, int userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.assignedTo = userId;
        this.createdAt = new Date();
        this.status = "未着手";
    }

  
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getAssignedTo() { return assignedTo; }
    public void setAssignedTo(int assignedTo) { this.assignedTo = assignedTo; }
    public String getAssigneeName() { return assigneeName; }
    public void setAssigneeName(String assigneeName) { this.assigneeName = assigneeName; }


    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }
    public String getTeamName() { return teamName;}

    public void setTeamName(String teamName) { this.teamName = teamName;}

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }
}
