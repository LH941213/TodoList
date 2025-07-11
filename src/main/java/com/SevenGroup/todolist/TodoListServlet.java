package com.SevenGroup.todolist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoListServlet
 */
@WebServlet("/tasks/list")
public class TodoListServlet extends HttpServlet {
	private TaskDao taskDao;
	@Override
	public void init() throws ServletException {
		taskDao = new TaskDao();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
