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
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Task task = taskDao.getTaskById(id);
                if (task != null) {
                    request.setAttribute("task", task);
                    request.getRequestDispatcher("/edit_task.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Task not found with ID: " + id);
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid task ID.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Failed to load task for editing: " + e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Task ID is missing for editing.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 // 获取参数
        String idParam = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean isCompleted = (request.getParameter("isCompleted") != null);
        

        // 验证必填字段
        if (idParam == null || idParam.trim().isEmpty() || title == null || title.trim().isEmpty()) {
            request.setAttribute("errorMessage", "任务ID和标题不能为空");
            if (idParam != null && !idParam.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idParam);
                    Task task = taskDao.getTaskById(id);
                    request.setAttribute("task", task); // 如果能加载到任务，设置它
                } catch (NumberFormatException | SQLException ex) {
                    // 忽略这里的异常，主错误信息已设置
                }
            }
            request.getRequestDispatcher("/edit_task.jsp").forward(request, response);
            return;
        }

        try {
            // 解析参数
            int id = Integer.parseInt(idParam);
            Task existingTask = taskDao.getTaskById(id);

            if (existingTask == null) {
                request.setAttribute("errorMessage", "未找到ID为 " + id + " 的任务");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
            
            existingTask.setTitle(title);
            existingTask.setDescription(description);
            existingTask.setCompleted(isCompleted); // 使用从请求中解析出的完成状态
            
         // updated_at 由 DAO 在执行 UPDATE 语句时使用数据库函数设置，所以这里不需要手动设置

            // 3. 调用 DAO 更新数据库
            taskDao.updateTask(existingTask);

            // 重定向到列表页
            response.sendRedirect(request.getContextPath() + "/tasks/list");
            
        } catch (NumberFormatException e) {
        	// ID 格式无效的错误处理
            request.setAttribute("errorMessage", "无效的任务ID格式");
             // 尝试重新加载任务数据以便在edit_task.jsp页面显示错误信息 (如果ID格式错误，这里就无法加载了)
            if (idParam != null && !idParam.trim().isEmpty()) {
                 try {
                     // 这里的 parseInt 会再次失败，但为了结构，保留获取参数的尝试
                     int id = Integer.parseInt(idParam); // 这一行会抛出 NumberFormatException
                     // 后面的代码不会执行
                 } catch (NumberFormatException ex) {
                     // 忽略此处的异常，因为已经在外面捕获了
                 }
            }
             request.getRequestDispatcher("/edit_task.jsp").forward(request, response);
        }
         catch (SQLException e) {
        	// 数据库错误处理
             e.printStackTrace(); // 打印异常堆栈，方便调试
             request.setAttribute("errorMessage", "数据库错误，更新任务失败: " + e.getMessage());
             // 尝试重新加载任务数据以便在edit_task.jsp页面显示错误信息和原有数据
              if (idParam != null && !idParam.trim().isEmpty()) {
                  try {
                      int id = Integer.parseInt(idParam);
                      Task task = taskDao.getTaskById(id);
                      request.setAttribute("task", task); // 如果能加载到任务，设置它
                  } catch (NumberFormatException | SQLException ex) {
                       // 忽略这里的异常，主错误信息已设置
                  }
              }
             request.getRequestDispatcher("/error.jsp").forward(request, response);
         }
        
    }
}
