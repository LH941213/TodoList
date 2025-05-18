package com.liuhe.todolist.servlet;

import java.io.IOException;

import com.liuhe.todolist.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		    String username = request.getParameter("username");
		    String password = request.getParameter("password");
		    String email = request.getParameter("email");
		    UserDao userDao = new UserDao();
		    
		    	try {
					// 使用 getUserByUsername 方法检查用户名是否已存在
					if (userDao.getUserByUsername(username) != null) {
					    request.setAttribute("message", "用户名已存在");
					    request.getRequestDispatcher("register.jsp").forward(request, response);
					    return;
					}
					 // 插入新用户
					    boolean success = userDao.insertUser(username, password, email);
					    if (success) {
					        response.sendRedirect("login.jsp"); // 注册成功跳转到登录页面
					    } else {
					        request.setAttribute("message", "注册失败，请重试");
					        request.getRequestDispatcher("register.jsp").forward(request, response);
					    }
				} catch (ServletException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

		    
		}
		
	}


