<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>チーム詳細</title>
    <link rel="stylesheet" href="css/styles.css" />
</head>
<body>

    <h2>チーム詳細</h2>
    <div class="team-info">
        <p><strong>チーム名：</strong> ${team.teamName}</p>
        <p><strong>説明：</strong> ${team.description}</p>
        <p><strong>作成者：</strong> ${team.creatorName}</p>
        <p><strong>作成日：</strong> ${team.createdAt}</p>
    </div>

    <hr/>

    <h3>👥 メンバー一覧</h3>
    <ul>
        <c:forEach var="user" items="${members}">
            <li>${user.name}（${user.role}） - ${user.email}</li>
        </c:forEach>
    </ul>

    <hr/>

    <h3>📋 タスク一覧</h3>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>タスク名</th>
            <th>担当者</th>
            <th>状態</th>
            <th>締切</th>
        </tr>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.title}</td>
                <td>${task.assigneeName}</td>
                <td>${task.status}</td>
                <td><c:out value="${task.deadline}" /></td>
            </tr>
        </c:forEach>
    </table>
	<form action="deleteTeam" method="post" onsubmit="return confirm('本当にこのチームを削除しますか？');">
    <input type="hidden" name="teamId" value="${team.teamId}" />
    <button type="submit">🗑 チーム削除</button>
</form>
    <br/>
    <a href="${pageContext.request.contextPath}/teamlist">← チーム一覧に戻る</a>
	
</body>
</html>