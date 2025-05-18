<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Todo List</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h1 style="text-align: center;">Todo List</h1>
    
    <!-- 新增任务按钮 -->
    <div style="text-align: center; margin: 20px;">
        <a href="${pageContext.request.contextPath}/add_task.jsp"><button>添加新任务</button></a>
        
    </div>
    <div style="text-align:right;margin:20px;">
    <a href="${pageContext.request.contextPath}/login.jsp"><button>登录</button></a>
     <a href="${pageContext.request.contextPath}/register.jsp"><button>注册</button></a>
    </div>
    <!-- 任务列表 -->
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Created At</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${tasks}" var="task">
            <tr>
                <td>${task.id}</td>
                <td>${task.title}</td>
                <td>${task.description}</td>
                <td>${task.completed ? 'Completed' : 'Pending'}</td>
                <td>${task.createdAt}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/tasks/edit?id=${task.id}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/tasks/delete?id=${task.id}" onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>