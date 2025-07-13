package com.SevenGroup.todolist.model;

public class User {
	private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String avatar;
    private String role;

 // Getter
 public String getRole() {
     return role;
 }

 // Setter
 public void setRole(String role) {
     this.role = role;
 }


    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getId() { 
    	return id; 
    	}
    public void setId(int id) {
    	this.id = id; 
    	}

    public String getUsername() {
    	return username; 
    	}
    public void setUsername(String username) { 
    	this.username = username; 
    	}

    public String getPassword() { 
    	return password; 
    	}
    public void setPassword(String password) { 
    	this.password = password;
    	}
}
