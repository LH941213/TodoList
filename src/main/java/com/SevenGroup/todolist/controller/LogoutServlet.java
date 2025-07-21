package com.SevenGroup.todolist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1️⃣ セッションを無効化する
        HttpSession session = request.getSession(false); // false: セッションがなければ作らない
        if (session != null) {
            session.invalidate(); // 破棄する
        }

        // 2️⃣ ログインページにリダイレクト
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}