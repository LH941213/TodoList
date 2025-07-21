package com.SevenGroup.todolist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SevenGroup.todolist.dao.TeamDao;
import com.SevenGroup.todolist.model.Team;

/**
 * Servlet implementation class TeamListServlet
 */
@WebServlet("/teamlist")
public class TeamListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
	        // 获取当前用户 ID
	        Integer userId = (Integer) request.getSession().getAttribute("userId");

	        TeamDao teamDao = new TeamDao();
	        List<Integer> teamIds = teamDao.getTeamIdsByUser(userId);

	        List<Team> teamList = new ArrayList<>();
	        for (int id : teamIds) {
	            Team team = teamDao.getTeamById(id);
	            if (team != null) {
	                teamList.add(team);
	            }
	        }

	        request.setAttribute("teamList", teamList);
	        request.getRequestDispatcher("teamlist.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace(); // 开发阶段建议保留，方便调试
	        request.setAttribute("errorMsg", "チーム一覧の取得中にエラーが発生しました");
	        request.getRequestDispatcher("error.jsp").forward(request, response);
	    }


	}

}
