package com.liuhe.todolist.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.liuhe.todolist.dao.TaskDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteTaskServlet extends HttpServlet{
	private TaskDao taskDao;

    @Override
    public void init() throws ServletException {
       
            taskDao = new TaskDao();
        
    }

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
