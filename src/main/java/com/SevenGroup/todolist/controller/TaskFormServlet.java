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
import com.SevenGroup.todolist.model.User;

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            TeamDao teamDao = new TeamDao();
            List<Team> teamList = teamDao.getTeamsByUser(userId); // 所有團隊
            request.setAttribute("teamList", teamList);

            // 👇 檢查 teamId 是否從前端傳來
            String teamIdStr = request.getParameter("teamId");
            if (teamIdStr != null && !teamIdStr.isEmpty()) {
                int teamId = Integer.parseInt(teamIdStr);
                List<User> members = teamDao.getTeamMembers(teamId); // 團隊成員
                Team team = teamDao.getTeamById(teamId);             // 團隊資訊

                request.setAttribute("teamId", teamId);
                request.setAttribute("members", members);
                request.setAttribute("team", team);
            }

            request.getRequestDispatcher("/add_task.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "チーム情報の取得に失敗しました：" + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("warningMessage", "teamIdが不正です");
            request.getRequestDispatcher("/add_task.jsp").forward(request, response);
        }
    }

}
