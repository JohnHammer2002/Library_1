<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Регистрация</title>
    <style>
        .error { color: red; }
        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], input[type="password"], input[type="email"] {
            width: 100%; padding: 8px; box-sizing: border-box;
        }
        button { padding: 8px 15px; background-color: #007bff; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="container">
    <h2>Регистрация нового пользователя</h2>

    <% if (request.getAttribute("errorMessage") != null) { %>
    <p class="error"><%= request.getAttribute("errorMessage") %></p>
    <% } %>

    <form method="post" action="${pageContext.request.contextPath}/register">
        <div class="form-group">
            <label for="username">Имя пользователя:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <button type="submit">Зарегистрироваться</button>
        </div>
    </form>

    <p>Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войти</a></p>
</div>
</body>
</html>