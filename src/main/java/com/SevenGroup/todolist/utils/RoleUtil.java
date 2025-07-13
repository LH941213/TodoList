package com.SevenGroup.todolist.utils;

public class RoleUtil {
	public static boolean isAdmin(String role) {
        return "admin".equalsIgnoreCase(role);
    }

    public static boolean isMember(String role) {
        return "member".equalsIgnoreCase(role);
    }


}
