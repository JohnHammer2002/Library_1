<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список книг</title>
    <style>
        .add-btn {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .add-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Список книг</h1>

<!-- Кнопка добавления новой книги -->
<a href="${pageContext.request.contextPath}/add-book-form" class="add-btn">
    Добавить новую книгу
</a>


<table>
    <!-- Существующая таблица с книгами -->
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Автор</th>
        <th>Издательство</th>
        <th>Страниц</th>
        <th>Описание</th>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.publisher}</td>
            <td>${book.pageNumber}</td>
            <td>${book.description}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>