<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>チーム一覧</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background-color: #f5f7fa;
      margin: 40px;
    }
    h2 {
      color: #2b7bb9;
      margin-bottom: 30px;
      text-align: center;
    }
    .team-container {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
      justify-content: center;
    }
    .team-card {
      background-color: white;
      border: 1px solid #ddd;
      border-left: 6px solid #2b7bb9;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
      padding: 20px;
      width: 320px;
      border-radius: 8px;
      transition: transform 0.2s ease;
    }
    .team-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    }
    .team-card h3 {
      margin: 0 0 10px;
      color: #333;
    }
    .team-card p {
      margin: 6px 0;
      color: #555;
    }
    .team-card a {
      display: inline-block;
      margin-top: 12px;
      padding: 8px 14px;
      background-color: #2b7bb9;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      transition: background-color 0.2s;
    }
    .team-card a:hover {
      background-color: #1d5d91;
    }
  </style>
</head>
<body>

  <h2>所属チーム一覧</h2>

  <div class="team-container">
    <c:forEach var="team" items="${teamList}">
      <div class="team-card">
        <h3>${team.teamName}</h3>
        <p>${team.description}</p>
        <p><strong>作成者：</strong>${team.creatorName}</p>
        <p><strong>作成日：</strong>${team.createdAt}</p>
        <a href="teamDetail.jsp?teamId=${team.teamId}">詳細を見る</a>
      </div>
    </c:forEach>
  </div>

</body>
</html>