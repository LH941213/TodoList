package com.SevenGroup.todolist.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SevenGroup.todolist.dao.TaskDao;
import com.SevenGroup.todolist.model.Task;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/addtask")
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
	       
	        String taskType = request.getParameter("taskType");
	        String teamIdStr = request.getParameter("teamId");
	        Integer userId = (Integer) request.getSession().getAttribute("userId");
	        
	    	if(userId==null) {
				request.setAttribute("errorMessage", "ログインしてください。");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
	    	if (title == null || title.trim().isEmpty()) {
	            request.setAttribute("warningMessage", "タイトルは空欄にできません");
	            request.getRequestDispatcher("/add_task.jsp").forward(request, response);
	            return;
	        }

	    	 try {
	             Task newTask = new Task();
	             newTask.setTitle(title);
	             newTask.setDescription(description);
	          // 👇 处理负责人字段
	             String assignedToStr = request.getParameter("assignedTo");
	             Integer assignedTo = (assignedToStr != null && !assignedToStr.isEmpty())
	                 ? Integer.parseInt(assignedToStr)
	                 : userId;
	             newTask.setAssignedTo(assignedTo);

	             
	             newTask.setUserId(userId);  // 作成者（如果你有这个字段）

	             if ("team".equals(taskType) && teamIdStr != null && !teamIdStr.isEmpty()) {
	                 int teamId = Integer.parseInt(teamIdStr);
	                 newTask.setTeamId(teamId); // 🔹 设定团队 ID，标识为团队任务
	             }

	             taskDao.addTask(newTask); // 🔹 插入数据库
	             response.sendRedirect(request.getContextPath() + "/tasklist");

	         } catch (SQLException e) {
	             e.printStackTrace();
	             request.setAttribute("errorMessage", "タスク登録に失敗しました：" + e.getMessage());
	             request.getRequestDispatcher("/error.jsp").forward(request, response);
	         } catch (NumberFormatException e) {
	             request.setAttribute("warningMessage", "チームIDが無効です");
	             request.getRequestDispatcher("/add_task.jsp").forward(request, response);
	         }

	}

}
