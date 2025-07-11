package com.liuhe.todolist.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.liuhe.todolist.dao.TaskDao;
import com.liuhe.todolist.model.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditTaskServlet  extends HttpServlet{
	private TaskDao taskDao;

    @Override
    public void init() throws ServletException {
        
            taskDao = new TaskDao();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        

        // 验证必填字段
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
