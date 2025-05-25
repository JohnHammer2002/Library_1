<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Библиотека книг</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            line-height: 1.6;
        }

        .action-panel {
            margin-bottom: 20px;
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
            align-items: center;
        }

        .btn {
            padding: 10px 15px;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }

        .btn-add {
            background-color: #4CAF50;
        }

        .btn-add:hover {
            background-color: #45a049;
        }

        .btn-edit {
            background-color: #2196F3;
        }

        .btn-edit:hover {
            background-color: #0b7dda;
        }

        .btn-delete {
            background-color: #f44336;
        }

        .btn-delete:hover {
            background-color: #da190b;
        }

        .search-form {
            margin-left: auto;
            display: flex;
            gap: 5px;
        }

        .search-form input {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .search-form button {
            padding: 8px 15px;
            background-color: #555;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 3px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
            position: sticky;
            top: 0;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .book-cover {
            max-width: 100px;
            max-height: 150px;
            border-radius: 4px;
        }

        .pagination {
            margin-top: 20px;
            text-align: center;
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .pagination a {
            padding: 8px 16px;
            text-decoration: none;
            color: #2196F3;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .pagination a:hover {
            background-color: #f5f5f5;
        }

        .pagination span {
            padding: 8px 16px;
            color: #555;
        }
    </style>
</head>
<body>
<h1>Библиотека книг</h1>

<!-- Панель действий -->
<div class="action-panel">
    <a href="views/add-book-form.jsp" class="btn btn-add">Добавить новую книгу</a>

    <form action="search" method="get" class="search-form">
        <input type="text" name="query" placeholder="Поиск по названию или автору">
        <button type="submit">Найти</button>
    </form>
</div>

<table>
    <thead>
    <tr>
        <th>Обложка</th>
        <th>Название</th>
        <th>Автор</th>
        <th>Издательство</th>
        <th>Страниц</th>
        <th>Выбрать</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${not empty book.imageUrl}">
                        <img src="${book.imageUrl}" alt="Обложка ${book.name}" class="book-cover">
                    </c:when>
                    <c:otherwise>
                        <img src="images/default-book.png" alt="Обложка отсутствует" class="book-cover">
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.publisher}</td>
            <td>${book.pageNumber}</td>
            <td>
                <input type="checkbox" name="selectedBooks" value="${book.id}">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Панель действий для выбранных книг -->
<div class="action-panel">
    <button type="button" class="btn btn-edit" onclick="editSelected()">Редактировать выбранное</button>
    <button type="button" class="btn btn-delete" onclick="deleteSelected()">Удалить выбранное</button>
</div>

<!-- Пагинация -->
<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="?page=${currentPage - 1}">Предыдущая</a>
    </c:if>

    <span>Страница ${currentPage} из ${totalPages}</span>

    <c:if test="${currentPage < totalPages}">
        <a href="?page=${currentPage + 1}">Следующая</a>
    </c:if>
</div>

<script>
    function editSelected() {
        const selected = document.querySelectorAll('input[name="selectedBooks"]:checked');
        if (selected.length === 1) {
            window.location.href = 'edit?id=' + selected[0].value;
        } else if (selected.length > 1) {
            alert('Пожалуйста, выберите только одну книгу для редактирования');
        } else {
            alert('Пожалуйста, выберите книгу для редактирования');
        }
    }

    function deleteSelected() {
        const selected = document.querySelectorAll('input[name="selectedBooks"]:checked');
        if (selected.length > 0) {
            if (confirm(`Вы уверены, что хотите удалить \${selected.length} выбранных книг?`)) {
                const ids = Array.from(selected).map(checkbox => checkbox.value).join(',');
                window.location.href = 'delete-multiple?ids=' + ids;
            }
        } else {
            alert('Пожалуйста, выберите книги для удаления');
        }
    }
</script>
</body>
</html>