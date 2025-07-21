<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>タスク追加</title>
<style>
    body {
        margin: 0;
        padding: 0;
        background-color: #f6f8fa;
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .task-card {
        background-color: #ffffff;
        padding: 35px;
        border-radius: 12px;
        box-shadow: 0 6px 18px rgba(0,0,0,0.08);
        width: 400px;
    }

    .task-card h1 {
        text-align: center;
        margin-bottom: 25px;
        color: #333;
    }

    label {
        display: block;
        margin-bottom: 6px;
        font-weight: bold;
        color: #555;
    }

    input[type="text"],
    textarea {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 14px;
        resize: vertical;
    }

    .buttons {
        display: flex;
        justify-content: space-between;
    }

    button {
        padding: 10px 18px;
        border: none;
        border-radius: 6px;
        font-size: 15px;
        cursor: pointer;
    }

    button[type="submit"] {
        background-color: #0078D4;
        color: white;
    }

    button[type="button"] {
        background-color: #6c757d;
        color: white;
    }

    .message {
        margin-top: 15px;
        color: red;
        text-align: center;
        font-size: 14px;
    }
</style>
<script>
    function toggleTeamSelector() {
        const type = document.querySelector('select[name="taskType"]').value;
        const teamBlock = document.getElementById("teamSelector");
        teamBlock.style.display = (type === "team") ? "block" : "none";
    }
 // 页面加载完后执行一次，以便初始判断
    window.onload = toggleTeamSelector;
        
</script>

</head>
<body>
     <div class="task-card">
        <h1>新しいタスクの追加</h1>

        <form action="${pageContext.request.contextPath}/addtask" method="post">
            <label>タイトル：</label>
            <input type="text" name="title" required>

            <label>説明：</label>
            <textarea name="description" rows="4" cols="50"></textarea>

            <label>タスク種類：</label>
            <select name="taskType" onchange="toggleTeamSelector()">
                <option value="personal">個人タスク</option>
                <option value="team">チームタスク</option>
            </select>

            <div id="teamSelector" style="display:block;">
                <label>所属チーム：</label>
                <select name="teamId">
                    <c:forEach var="team" items="${teamList}">
                        <option value="${team.teamId}">${team.teamName}</option>

                        
                    </c:forEach>
                </select>
            </div>
			<!-- 👇 新增责任人选择 -->
			<div id="assigneeBlock" style="display: block;">
			    <label>担当者：</label>
			    <select name="assignedTo">
			        <option value="">選択してください</option>
			        <c:forEach var="user" items="${members}">
			            <option value="${user.id}">${user.name}</option>
			        </c:forEach>
			    </select>
			</div>
			
            <div class="buttons">
                <button type="submit">保存</button>
                <button type="button" onclick="location.href='${pageContext.request.contextPath}/index.jsp'">キャンセル</button>
            </div>
        </form>

        <c:if test="${not empty warningMessage}">
            <div class="message">${warningMessage}</div>
        </c:if>
    </div>
    
</body>
</html>