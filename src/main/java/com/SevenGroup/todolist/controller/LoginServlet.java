package com.SevenGroup.todolist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SevenGroup.todolist.dao.UserDao;
import com.SevenGroup.todolist.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
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
	        	// セッションを作成し、ユーザー情報を保存する。
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user); 
	            session.setAttribute("userId", user.getId());
	            session.setAttribute("userRole", user.getRole());

	            
	
	
	            response.sendRedirect(request.getContextPath() + "/tasklist");
	           
	            
	        } else {
	            response.sendRedirect("login.jsp?error=true");
	        		}
        }catch(Exception e){
    	   e.printStackTrace();  
           System.out.println("ログイン処理でエラーが発生しました：" + e.getMessage());
           response.sendRedirect("login.jsp?error=true"); 
       }
	}

}
