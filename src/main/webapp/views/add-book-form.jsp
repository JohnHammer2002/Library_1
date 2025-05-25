<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добавить книгу</title>
    <style>
        .form-container {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .submit-btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Добавить новую книгу</h2>
    <form action="<%= request.getContextPath() %>/add-book" method="post">
        <div class="form-group">
            <label for="name">Название:</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="author">Автор:</label>
            <input type="text" id="author" name="author" required>
        </div>

        <div class="form-group">
            <label for="publisher">Издательство:</label>
            <input type="text" id="publisher" name="publisher">
        </div>

        <div class="form-group">
            <label for="pageNumber">Количество страниц:</label>
            <input type="number" id="pageNumber" name="pageNumber" min="1">
        </div>

        <div class="form-group">
            <label for="description">Описание:</label>
            <textarea id="description" name="description" rows="4"></textarea>
        </div>

        <button type="submit" class="submit-btn">Добавить книгу</button>
    </form>
</div>
</body>
</html>
