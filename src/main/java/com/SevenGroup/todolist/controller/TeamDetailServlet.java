package com.SevenGroup.todolist.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SevenGroup.todolist.dao.TaskDao;
import com.SevenGroup.todolist.dao.TeamDao;
import com.SevenGroup.todolist.model.Task;
import com.SevenGroup.todolist.model.Team;
import com.SevenGroup.todolist.model.User;

@WebServlet("/teamdetail")
public class TeamDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        try{int teamId = Integer.parseInt(request.getParameter("teamId"));

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(teamId);
        TaskDao taskDao=new TaskDao();
        
        List<User> members = teamDao.getTeamMembers(teamId); // 可选
        List<Task> tasks = taskDao.getTasksByTeamId(teamId); // 可选

        request.setAttribute("team", team);
        request.setAttribute("members", members);
        request.setAttribute("tasks", tasks);

        request.getRequestDispatcher("/teamdetail.jsp").forward(request, response);
        }catch(Exception e) {
        	e.printStackTrace();
            request.setAttribute("errorMessage", "チーム詳細の取得中にエラーが発生しました: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);

        }
    }
}