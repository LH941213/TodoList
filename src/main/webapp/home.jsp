<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>TodoList 管理プラットフォームへようこそ</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #e9f5ff, #fefefe);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            color: #333;
        }
        h1 {
            font-size: 42px;
            margin-bottom: 10px;
        }
        p {
            font-size: 18px;
            margin-bottom: 30px;
        }
        .buttons a {
            text-decoration: none;
            margin: 0 10px;
        }
        .buttons button {
            padding: 12px 24px;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .buttons button:hover {
            background-color: #0056b3;
        }
        .logo {
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: bold;
            color: #007bff;
        }
    </style>
</head>
<body>

    <div class="logo">📝 WebTodoList 管理プラットフォーム</div>
    <h1>効率的な暮らしを始めましょう</h1>
    <p>TodoListツールを使って毎日のタスクを整理し、生産性を高めましょう</p>

    <div class="buttons">
        <a href="${pageContext.request.contextPath}/login.jsp"><button>ログイン</button></a>
        <a href="${pageContext.request.contextPath}/register.jsp"><button>新規登録</button></a>
    </div>

</body>
</html>