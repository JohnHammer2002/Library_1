<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Добавить новую книгу</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      line-height: 1.6;
    }

    .form-container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 5px;
      background-color: #f9f9f9;
    }

    h1 {
      text-align: center;
      color: #333;
    }

    .form-group {
      margin-bottom: 15px;
    }

    label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }

    input[type="text"],
    input[type="number"],
    textarea {
      width: 100%;
      padding: 8px;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box;
    }

    textarea {
      height: 100px;
      resize: vertical;
    }

    .btn-group {
      margin-top: 20px;
      display: flex;
      justify-content: space-between;
    }

    .btn {
      padding: 10px 15px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 14px;
      text-decoration: none;
      text-align: center;
    }

    .btn-submit {
      background-color: #4CAF50;
      color: white;
    }

    .btn-submit:hover {
      background-color: #45a049;
    }

    .btn-cancel {
      background-color: #f44336;
      color: white;
    }

    .btn-cancel:hover {
      background-color: #da190b;
    }

    .error-message {
      color: #f44336;
      font-size: 14px;
      margin-top: 5px;
    }

    .required-field::after {
      content: " *";
      color: #f44336;
    }
  </style>
</head>
<body>
<div class="form-container">
  <h1>Добавить новую книгу</h1>

  <form action="<%= request.getContextPath() %>/add-book" method="post" onsubmit="return validateForm()">
    <div class="form-group">
      <label for="name" class="required-field">Название книги</label>
      <input type="text" id="name" name="name" required>
      <div id="name-error" class="error-message"></div>
    </div>

    <div class="form-group">
      <label for="author" class="required-field">Автор</label>
      <input type="text" id="author" name="author" required>
      <div id="author-error" class="error-message"></div>
    </div>

    <div class="form-group">
      <label for="publisher">Издательство</label>
      <input type="text" id="publisher" name="publisher">
    </div>

    <div class="form-group">
      <label for="pageNumber">Количество страниц</label>
      <input type="number" id="pageNumber" name="pageNumber" min="1">
      <div id="pageNumber-error" class="error-message"></div>
    </div>

    <div class="form-group">
      <label for="description">Описание</label>
      <textarea id="description" name="description"></textarea>
    </div>

    <div class="form-group">
      <label for="imageUrl">URL обложки</label>
      <input type="text" id="imageUrl" name="imageUrl" placeholder="https://example.com/image.jpg">
    </div>

    <div class="btn-group">
      <button type="submit" class="btn btn-submit">Добавить книгу</button>
      <a href="${pageContext.request.contextPath}/books" class="btn btn-cancel">Отмена</a>
    </div>
  </form>
</div>

<script>
  function validateForm() {
    let isValid = true;

    // Валидация названия
    const nameInput = document.getElementById('name');
    const nameError = document.getElementById('name-error');
    if (nameInput.value.trim() === '') {
      nameError.textContent = 'Пожалуйста, введите название книги';
      isValid = false;
    } else {
      nameError.textContent = '';
    }

    // Валидация автора
    const authorInput = document.getElementById('author');
    const authorError = document.getElementById('author-error');
    if (authorInput.value.trim() === '') {
      authorError.textContent = 'Пожалуйста, введите автора книги';
      isValid = false;
    } else {
      authorError.textContent = '';
    }

    // Валидация количества страниц
    const pageNumberInput = document.getElementById('pageNumber');
    const pageNumberError = document.getElementById('pageNumber-error');
    if (pageNumberInput.value && parseInt(pageNumberInput.value) <= 0) {
      pageNumberError.textContent = 'Количество страниц должно быть положительным числом';
      isValid = false;
    } else {
      pageNumberError.textContent = '';
    }

    return isValid;
  }
</script>
</body>
</html>
