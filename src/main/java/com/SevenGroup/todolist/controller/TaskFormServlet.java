package com.SevenGroup.todolist.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SevenGroup.todolist.dao.TeamDao;
import com.SevenGroup.todolist.model.Team;

/**
 * Servlet implementation class TaskFormServlet
 */
@WebServlet("/taskform")
public class TaskFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamDao teamDao;
	
	@Override
    public void init() throws ServletException {
        teamDao = new TeamDao();
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Integer userId = (Integer) request.getSession().getAttribute("userId");
	        if (userId == null) {
	            response.sendRedirect(request.getContextPath() + "/login.jsp");
	            return;
	        }

	        try {
	            List<Team> teamList = teamDao.getTeamsByUser(userId); // 获取当前用户参与的团队
	            System.out.println("取得的团队数量：" + teamList.size());
	            request.setAttribute("teamList", teamList);
	            request.getRequestDispatcher("/add_task.jsp").forward(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "チーム情報の取得に失敗しました");
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        }

	}

}
