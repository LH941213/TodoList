package com.SevenGroup.todolist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SevenGroup.todolist.dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username = request.getParameter("username");
		    String password = request.getParameter("password");
		    String email = request.getParameter("email");
		    UserDao userDao = new UserDao();
		    
		    try {
				
				if (userDao.getUserByUsername(username) != null) {
				    request.setAttribute("message", "用户名已存在");
				    request.getRequestDispatcher("register.jsp").forward(request, response);
				    return;
				}
				 
				    boolean success = userDao.insertUser(username, password, email);
				    if (success) {
				        response.sendRedirect("login.jsp"); 
				    } else {
				        request.setAttribute("message", "注册失败，请重试");
				        request.getRequestDispatcher("register.jsp").forward(request, response);
				    }
			} catch (ServletException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}

}
