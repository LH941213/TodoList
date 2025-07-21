package com.SevenGroup.todolist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SevenGroup.todolist.dao.UserDao;
import com.SevenGroup.todolist.model.User;

@WebServlet("/adduser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        // 🔹 取得前端表單資料
        String username = request.getParameter("username");
        String name     = request.getParameter("name");
        String email    = request.getParameter("email");
        String role     = request.getParameter("role");
        String password = request.getParameter("password");

        // 🔹 檢查是否有必要欄位為空（可選加強）
        if (username == null || username.trim().isEmpty()
            || password == null || password.trim().isEmpty()
            || email == null || email.trim().isEmpty()) {

            request.setAttribute("errorMessage", "必要項目が未入力です。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // 🔹 建立 User 物件
        User user = new User();
        user.setUsername(username.trim());
        user.setName(name != null ? name.trim() : null);
        user.setEmail(email.trim());
        user.setRole(role != null ? role.trim() : "member"); // 預設為 member
        user.setPassword(password.trim()); // ⚠️ 可加密再儲存

        // 🔹 呼叫 DAO 插入使用者
        UserDao userDao = new UserDao();
        boolean success = userDao.insertUser(user); // ← 你的 insertUser(User user) 方法

        // 🔹 判斷結果並跳轉
        if (success) {
            response.sendRedirect(request.getContextPath() + "/edituser"); // ✅ 成功後返回管理頁
        } else {
            request.setAttribute("errorMessage", "ユーザー登録に失敗しました。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}