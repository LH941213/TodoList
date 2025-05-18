package com.liuhe.todolist.servlet;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.liuhe.todolist.dao.TaskDao;
import com.liuhe.todolist.model.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TodoListServlet extends HttpServlet {
	private TaskDao taskDao;
	@Override
	public void init() throws ServletException {
		taskDao = new TaskDao();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Integer userId =(Integer)request.getSession().getAttribute("userId");
		if(userId==null) {
			request.setAttribute("errorMessage", "请先登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		try {
			
			List<Task> tasks = taskDao.getTasksByUserId(userId);
			
			request.setAttribute("tasks", tasks);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
            request.setAttribute("errorMessage", "任务加载失败" + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
		} 
	}
}
