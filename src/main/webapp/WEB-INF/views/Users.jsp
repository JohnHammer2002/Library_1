<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Управление пользователями</title>
    <style>
        .user-table { width: 100%; border-collapse: collapse; }
        .user-table th, .user-table td { padding: 8px; border: 1px solid #ddd; }
        .user-table tr:nth-child(even) { background-color: #f2f2f2; }
        .delete-btn { background-color: #dc3545; color: white; border: none; padding: 5px 10px; cursor: pointer; }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="container">
    <h2>Список пользователей</h2>

    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <table class="user-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Имя пользователя</th>
            <th>Email</th>
            <th>Роль</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/delete-user" method="post"
                          onsubmit="return confirm('Вы уверены, что хотите удалить этого пользователя?');">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit" class="delete-btn">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>