package com.SevenGroup.todolist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SevenGroup.todolist.dao.TeamDao;
import com.SevenGroup.todolist.dao.TeamMemberDao;
import com.SevenGroup.todolist.dao.UserDao;
import com.SevenGroup.todolist.model.User;

/**
 * Servlet implementation class PrepareCreateTeamServlet
 */
@WebServlet("/addteam")
public class AddTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// ① 获取当前登录者的 ID（从 session）
        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            // 未登录：跳转到登录页面
            response.sendRedirect("login.jsp");
            return;
        }
        int currentUserId = Integer.parseInt(userIdObj.toString());

        // ② 从数据库获取全部用户
        UserDao userDao = new UserDao(); // 确保你有这个 DAO 类
        List<User> allUsers = userDao.getAllUsers(); // 你可以修改方法名为实际的

        // ③ 排除当前用户
        List<User> filteredUsers = allUsers.stream()
                .filter(u -> u.getId() != currentUserId)
                .collect(Collectors.toList());

        // ④ 把成员列表设置进 request，传给 JSP 页面
        request.setAttribute("userList", filteredUsers);

        // ⑤ 转发到 JSP 页面
        request.getRequestDispatcher("/add_team.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
    	try {
		        HttpSession session = request.getSession();
		
		        // 创建者 ID（session 获取）
		        Integer creatorId = (Integer) session.getAttribute("userId");
		        
		        if (creatorId==null) {
		            response.sendRedirect("login.jsp");
		            return;
		        }
		
		        // 表单参数
		        String teamName = request.getParameter("teamName");
		        String description = request.getParameter("description");
		        String[] memberIds = request.getParameterValues("members");
		
		        // 创建团队，返回 team_id
		        TeamDao teamDao = new TeamDao();
		        int teamId = teamDao.createTeam(teamName, description, creatorId);
		
		        // 构造成员列表（包括创建者本人）
		        
		        List<Integer> otherMemberList = new ArrayList<>();
		       
		        if (memberIds != null) {
		            for (String idStr : memberIds) {
		                int id=Integer.parseInt(idStr);
		                if(id!=creatorId) 
		                {
		                	otherMemberList.add(id);
		                }
		            }
		        }
		        
		        // 添加成员数据
		        TeamMemberDao memberDao = new TeamMemberDao();
		        memberDao.addMembersToTeam(teamId, List.of(creatorId), "admin");
		        memberDao.addMembersToTeam(teamId, otherMemberList, "member");
		
		        // 跳转页面
		        response.sendRedirect(request.getContextPath() + "/teamlist");
    	}catch(Exception e) 
    	{
    		e.printStackTrace(); // ✔ 开发阶段可打印
            request.setAttribute("errorMsg", "チーム作成中にエラーが発生しました");
            request.getRequestDispatcher("error.jsp").forward(request, response);

    	}

    }
	}


