<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.SevenGroup.todolist.utils.RoleUtil" %>

<c:set var="user" value="${sessionScope.user}" />	

<!DOCTYPE html>
<html>
<head>
<title>Todoリスト</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
}

.container {
	display: flex;
	height: 100vh;
}

.sidebar {
	width: 250px;
	background-color: #343a40;
	color: white;
	padding: 20px;
	box-sizing: border-box;
}

.sidebar img {
	border-radius: 50%;
	width: 80px;
	height: 80px;
	object-fit: cover;
	display: block;
	margin: 0 auto;
}

.sidebar h2 {
	text-align: center;
	margin-top: 10px;
	font-size: 18px;
}

.sidebar .actions {
	margin-top: 30px;
	text-align: center;
}

.sidebar .actions button {
	padding: 10px 16px;
	margin-top: 10px;
	border: none;
	border-radius: 4px;
	background-color: #007bff;
	color: white;
	cursor: pointer;
}

.main-content {
	flex: 1;
	padding: 30px;
	box-sizing: border-box;
}

h1 {
	color: #333;
	margin-bottom: 20px;
}

table {
	border-collapse: collapse;
	width: 100%;
	background-color: white;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

th, td {
	border: 1px solid #dee2e6;
	padding: 12px;
	text-align: left;
}

th {
	background-color: #e9ecef;
}

.table-actions a {
	padding: 6px 12px;
	margin-right: 5px;
	background-color: #17a2b8;
	color: white;
	text-decoration: none;
	border-radius: 3px;
}

.table-actions a.delete {
	background-color: #dc3545;
}

.user-dropdown {
	text-align: center;
	margin-top: 20px;
	position: relative;
}

.user-toggle {
	cursor: pointer;
	display: inline-block;
	width: 100%;
	padding: 10px;
	border-radius: 6px;
	background-color: #495057;
	color: white;
}

.user-toggle img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	vertical-align: middle;
	margin-right: 10px;
}

.user-toggle span {
	vertical-align: middle;
	font-size: 16px;
}

.caret {
	float: right;
	font-size: 12px;
	margin-top: 4px;
}

.dropdown-menu {
	display: none;
	position: absolute;
	top: 100%;
	left: 50%;
	transform: translateX(-50%);
	background-color: white;
	list-style: none;
	padding: 0;
	margin-top: 6px;
	border-radius: 5px;
	box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
	width: 150px;
	z-index: 99;
}

.dropdown-menu li {
	border-bottom: 1px solid #eee;
}

.dropdown-menu li a {
	display: block;
	padding: 10px;
	color: #333;
	text-decoration: none;
}

.dropdown-menu li a:hover {
	background-color: #f1f1f1;
}
.user-toggle {
    display: flex;
    align-items: center;
    gap: 10px; /* 控制头像和文字之间的间距 */
}

.avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    object-fit: cover;
}

.user-name {
    font-weight: bold;
    font-size: 16px;
}

.actions {
    display: flex;
    flex-direction: column; /* 垂直排列按钮 */
    gap: 10px;               /* 按钮之间间距 */
    width: 220px;            /* 整体固定宽度，更整齐 */
    margin-top: 15px;
}

.actions a button {
    width: 100%;
    padding: 10px;
    font-size: 14px;
    background-color: #2b7bb9;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: 0.3s;
}

.actions a button:hover {
    background-color: #1d5d91;
}

</style>
</head>
<body>
	
	
	<div class="container">
		<!-- 左側のサイドバー -->
		<div class="sidebar">
			<!-- ユーザードロップダウン -->
						<%
						    Integer userId = (Integer) session.getAttribute("userId");
						    if (userId == null) {
						%>
				    <!-- 🔒 未登录时显示登录按钮 -->
				    <div class="user-dropdown">
				        <a href="${pageContext.request.contextPath}/login.jsp">
				            <button class="login-btn">ログイン</button>
				        </a>
				    </div>
							<%
							    } else {
							%>
				    <!-- ✅ 已登录时显示用户信息下拉 -->
				    <div class="user-dropdown">
				        <div class="user-toggle" onclick="toggleDropdown()">
				            <img src="${pageContext.request.contextPath}/images/${user.avatar}" alt="アバター" class="avatar">
				            <span class="user-name">${user.name}</span>
				            <span class="caret">▼</span>
				        </div>
				        <ul class="dropdown-menu" id="dropdownMenu">
				            <li><a href="${pageContext.request.contextPath}/setting.jsp">設定</a></li>
				            <li><a href="${pageContext.request.contextPath}/logout">ログアウト</a></li> <!-- 🔁 可选：添加 logout 处理 -->
				        </ul>
				    </div>
				<%
				    }
				%>
						
			<div class="actions">
				<a href="${pageContext.request.contextPath}/taskform">
    			<button>タスクを追加</button>
				</a>

				<% 
					String role = (String) session.getAttribute("userRole");
				    if (role != null &&RoleUtil.isAdmin(role)) {

				%>
  					<a href="${pageContext.request.contextPath}/edituser">
    				<button>ユーザー管理</button>
  					</a>
  					<a href="${pageContext.request.contextPath}/addteam">
        			<button>チーム作成</button>
    				</a>
  					

			        <a href="${pageContext.request.contextPath}/teamlist">
			            <button>チーム一覧</button>
			        </a>
				
			        
  					
				<% } %>
				
			</div>
		</div>

		<!-- 右側のコンテンツ -->
		<div class="main-content">
			<h1>Todoリスト</h1>
			<table>
				<tr>
					 <th>ID</th>
					  <th>タイトル</th>
					  <th>詳細</th>
					  <th>種別</th> <!-- 🔸 NEW: 個人 or チーム -->
					  <th>チーム名</th> <!-- 🔸 NEW: 所属チーム -->
					  <th>ステータス</th>
					  <th>作成日時</th>
					  <th>操作</th>

				</tr>
				<c:forEach items="${tasks}" var="task">
					<tr>
						<td>${task.id}</td>
						<td>${task.title}</td>
						<td>${task.description}</td>
						 <td>
					      <c:choose>
					        <c:when test="${task.teamId != null}">
					          チーム
					        </c:when>
					        <c:otherwise>
					          個人
					        </c:otherwise>
					      </c:choose>
					    </td>
						<td>
					      <c:out value="${task.teamName != null ? task.teamName : '-'}" />
					    </td>
						
						<td>${task.completed ? '完了' : '未完了'}</td>
						<td>${task.createdAt}</td>
						<td class="table-actions">
							<a href="${pageContext.request.contextPath}/edittask?id=${task.id}">編集</a>
							<a href="${pageContext.request.contextPath}/deltask?id=${task.id}"
							   onclick="return confirm('このタスクを本当に削除しますか？')" class="delete">削除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script>
		function toggleDropdown() {
			var menu = document.getElementById("dropdownMenu");
			menu.style.display = (menu.style.display === "block") ? "none" : "block";
		}
	</script>
</body>

</html>