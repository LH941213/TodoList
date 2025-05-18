package com.liuhe.todolist.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.liuhe.todolist.dao.TaskDao;
import com.liuhe.todolist.model.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddTaskServlet  extends HttpServlet{
	private TaskDao taskDao;
	@Override
    public void init() throws ServletException {
       
            taskDao = new TaskDao();
        
    }
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
    	if(userId==null) {
			request.setAttribute("errorMessage", "请先登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
        if (title != null && !title.trim().isEmpty()) {
            Task newTask = new Task(title, description,userId);
            try {
                taskDao.addTask(newTask);
                response.sendRedirect(request.getContextPath() + "/tasks/list");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Failed to add task: " + e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("warningMessage", "Title cannot be empty.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
