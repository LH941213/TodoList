package com.SevenGroup.todolist.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SevenGroup.todolist.dao.TaskDao;

/**
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet("/tasks/delete")
public class DeleteTaskServlet extends HttpServlet {
	private TaskDao taskDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		  taskDao = new TaskDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String idParam = request.getParameter("id");
	        Integer userId = (Integer) request.getSession().getAttribute("userId");
		if(userId==null) {
			request.setAttribute("errorMessage", "请先登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                taskDao.deleteTask(id,userId);
                response.sendRedirect(request.getContextPath() + "/tasks/list");
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid task ID.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Failed to delete task: " + e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Task ID is missing.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
		
	}

}
