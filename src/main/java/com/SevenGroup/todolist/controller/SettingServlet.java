package com.SevenGroup.todolist.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.SevenGroup.todolist.dao.UserDao;
import com.SevenGroup.todolist.model.User;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/setting")
@MultipartConfig

public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SettingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String action = null;

		for (Part part : request.getParts()) {
			if ("action".equals(part.getName())) {
				action = new String(part.getInputStream().readAllBytes(), "UTF-8").trim();
				break;
			}
		}
		try {
			switch (action) {
			case "updateProfile":
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String oldPwd = request.getParameter("oldPassword");
				String newPwd = request.getParameter("newPassword");
				String confirmPwd = request.getParameter("confirmPassword");
				// 检查旧密码、修改名称、邮箱等
				if (newPwd != null && !newPwd.isEmpty()) {

				if (!user.getPassword().equals(oldPwd)) {
					request.setAttribute("errorMessage", "当前密码错误！");
					request.getRequestDispatcher("setting.jsp").forward(request, response);
					return;
				}
				
					if (!newPwd.equals(confirmPwd)) {
						request.setAttribute("errorMessage", "新密码两次输入不一致！");
						request.getRequestDispatcher("setting.jsp").forward(request, response);
						return;
					}
					user.setPassword(newPwd); // 更新密码
				}
				Part avatarPart = request.getPart("avatar");
				
				
				if (avatarPart != null && avatarPart.getSize() > 0) {
					String originalName = Paths.get(avatarPart.getSubmittedFileName()).getFileName().toString();

					String fileName = UUID.randomUUID().toString() + "_" + originalName;
					String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists())
						uploadDir.mkdir();
					
					avatarPart.write(uploadPath + File.separator + fileName);
					user.setAvatar(fileName);

				}
				user.setName(name);
				user.setEmail(email);
				UserDao.updateProfile(user);
				session.setAttribute("user", user);
				request.setAttribute("successMessage", "资料已成功更新！");
				request.getRequestDispatcher("setting.jsp").forward(request, response);

				break;

			case "deleteAvatar":
				user.setAvatar("default.jpg"); // 设置为默认头像
				// 更新数据库
				UserDao.updateProfile(user);
				session.setAttribute("user", user);
				request.setAttribute("successMessage", "头像已重置！");
				request.getRequestDispatcher("setting.jsp").forward(request, response);

				break;

			
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "操作失败：" + e.getMessage());
			request.getRequestDispatcher("setting.jsp").forward(request, response);

		}

	}

}
