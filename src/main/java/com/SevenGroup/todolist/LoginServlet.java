package com.SevenGroup.todolist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		try{String username = request.getParameter("username").trim();
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
