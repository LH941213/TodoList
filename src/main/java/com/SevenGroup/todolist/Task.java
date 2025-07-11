package com.SevenGroup.todolist;
import java.util.Date;
public class Task {
	private int id;
	private String title;
	private String description;
    private boolean isCompleted =false;
    private Date createdAt =new Date();
    private Date updatedAt; 
    private int userId; 
    public Task() {}
    
    public Task(String title, String description,int userId) {
        this.title = title;
        this.description = description;
        this.isCompleted = false;  // 默认未完成
        this.createdAt = new Date(); // 自动设置创建时间
        this.userId=userId;
    }
    public Task(int id, String title, String description, boolean isCompleted, Date createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

}
