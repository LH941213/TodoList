<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="user" value="${sessionScope.user}" />

<!DOCTYPE html>
<html>
<head>
    <title>アカウント設定</title>
    <style>
        body { font-family: Arial; padding: 30px; background-color: #f5f5f5; }
        .settings-box { background: #fff; padding: 20px; max-width: 600px; margin: auto; border-radius: 10px; box-shadow: 0 0 10px #ccc; }
        .settings-box h2 { margin-bottom: 20px; }
        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px;
        }
        .avatar-preview { width: 100px; height: 100px; border-radius: 50%; object-fit: cover; margin-bottom: 10px; }
        .action-buttons button { margin-right: 10px; }
    </style>
</head>
<body>
    <div class="settings-box">
        <h2>アカウント設定</h2>
        <form action="setting" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="action" value="updateProfile">

            <img class="avatar-preview" src="images/${user.avatar}" alt="アバター">
            <br>
            <input type="file" name="avatar"> <br>
            <button type="submit" name="action" value="deleteAvatar">アバターを削除</button>

            <h3>基本情報</h3>
            <input type="text" name="name" value="${user.name}" placeholder="ユーザー名">
            <input type="email" name="email" value="${user.email}" placeholder="メールアドレス">

            <h3>パスワード変更</h3>
            <input type="password" name="oldPassword" placeholder="現在のパスワード">
            <input type="password" name="newPassword" placeholder="新しいパスワード">
            <input type="password" name="confirmPassword" placeholder="新しいパスワード（確認）">

            <div class="action-buttons">
                <button type="submit" name="action" value="updateProfile">変更を保存</button>
            </div>
        </form>
    </div>

    <c:if test="${not empty successMessage}">
        <div style="color:green;">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div style="color:red;">${errorMessage}</div>
    </c:if>
</body>
</html>