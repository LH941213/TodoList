package com.SevenGroup.todolist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SevenGroup.todolist.dao.UserDao;
import com.SevenGroup.todolist.model.User;

/**
 * Servlet implementation class UserAdminServlet
 */
@WebServlet("/edituser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
        List<User> allUsers = userDao.getAllUsers();  // 你之前新加的方法

        request.setAttribute("userlist", allUsers);
        request.getRequestDispatcher("/userlist.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取提交参数
        int userId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");  // "admin" or "member"

        // 更新用户对象
        User user = new User();
        user.setId(userId);
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);

        try {
            UserDao.updateProfile(user);  // 你已有的方法，会更新 name、email、role
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 提交后跳回管理页面（刷新列表）
        response.sendRedirect("edituser");

	}

}
