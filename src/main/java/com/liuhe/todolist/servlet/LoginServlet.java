package com.liuhe.todolist.servlet;

import java.io.IOException;

import com.liuhe.todolist.dao.UserDao;
import com.liuhe.todolist.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDAO = new UserDao();
        User user = userDAO.getUserByUsername(username);
        

	     if (user != null && user.getPassword().equals(password)) { 
	        	// 创建 Session，并存储用户信息
	            HttpSession session = request.getSession();
	            session.setAttribute("userId", user.getId()); 
	            session.setAttribute("username", user.getUsername()); 
	
	
	            response.sendRedirect(request.getContextPath() + "/tasks/list");
	           
	            
	        } else {
	            response.sendRedirect("login.jsp?error=true");
	        		}
        }catch(Exception e){
    	   e.printStackTrace();  
           System.out.println("❌ 登录处理发生错误：" + e.getMessage());
           response.sendRedirect("login.jsp?error=true"); 
       }
		
		
	}

}
