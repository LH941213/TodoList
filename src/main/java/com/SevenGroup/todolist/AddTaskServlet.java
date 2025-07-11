package com.SevenGroup.todolist;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/tasks/add")
public class AddTaskServlet extends HttpServlet {
	private TaskDao taskDao;
	@Override
    public void init() throws ServletException {
       
            taskDao = new TaskDao();
        
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
	            request.setAttribute("warningMessage", "タイトルは空欄にできません");
	            request.getRequestDispatcher("/index.jsp").forward(request, response);
	        }
	}

}
