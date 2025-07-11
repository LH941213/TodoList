package com.SevenGroup.todolist;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/tasks/edit")
public class EditTaskServlet extends HttpServlet {
	private TaskDao taskDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init() throws ServletException {
		 taskDao = new TaskDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		if(userId==null) {
			request.setAttribute("errorMessage", "请先登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		String idParam = request.getParameter("id");
		if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Task task = taskDao.getTaskById(id, userId);
                if (task != null) {
                    request.setAttribute("task", task);
                    request.getRequestDispatcher("/edit_task.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "未找到该任务或无权限访问");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "无效的任务ID.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "加载任务失败: " + e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "任务ID缺失");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 

		 Integer userId = (Integer) request.getSession().getAttribute("userId");
		 if(userId==null) {
	 			request.setAttribute("errorMessage", "请先登录");
	 			request.getRequestDispatcher("/login.jsp").forward(request, response);
	 			return;
	 		}
		 String idParam = request.getParameter("id");
	        String title = request.getParameter("title");
	        String description = request.getParameter("description");
	        boolean isCompleted = (request.getParameter("isCompleted") != null);
	     if (idParam == null || idParam.trim().isEmpty() || title == null || title.trim().isEmpty()) {
	            request.setAttribute("errorMessage", "任务ID和标题不能为空");
	           
	            request.getRequestDispatcher("/edit_task.jsp").forward(request, response);
	            return;
	        }
	     try {
	            // 解析参数
	            int id = Integer.parseInt(idParam);
	            Task existingTask = taskDao.getTaskById(id,userId);

	            if (existingTask == null) {
	                request.setAttribute("errorMessage", "未找到ID为 " + id + " 的任务");
	                request.getRequestDispatcher("/error.jsp").forward(request, response);
	                return;
	            }
	            
	            existingTask.setTitle(title);
	            existingTask.setDescription(description);
	            existingTask.setCompleted(isCompleted); 
	            
	        
	            taskDao.updateTask(existingTask,userId);

	           
	            request.setAttribute("successMessage", "任务更新成功！");
	            response.sendRedirect(request.getContextPath() + "/tasks/list");
	            
	        } catch (NumberFormatException e) {
	        	
	            request.setAttribute("errorMessage", "无效的任务ID格式");
	             
	             request.getRequestDispatcher("/edit_task.jsp").forward(request, response);
	        }
	         catch (SQLException e) {
	        	
	             e.printStackTrace(); 
	             request.setAttribute("errorMessage", "数据库错误，更新任务失败: " + e.getMessage());
	            
	             request.getRequestDispatcher("/error.jsp").forward(request, response);
	         }
	}

}
