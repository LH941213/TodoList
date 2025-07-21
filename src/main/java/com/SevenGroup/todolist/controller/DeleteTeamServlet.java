package com.SevenGroup.todolist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SevenGroup.todolist.dao.TeamDao;

@WebServlet("/deleteTeam")
public class DeleteTeamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int teamId = Integer.parseInt(request.getParameter("teamId"));

        try {
            TeamDao teamDao = new TeamDao();
            teamDao.deleteTeam(teamId);

            // 删除成功后跳转回列表页
            response.sendRedirect("teamlist");  // ✨ teamList 是 Servlet 映射路径
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "チーム削除中にエラーが発生しました: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
