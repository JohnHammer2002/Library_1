<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav style="background-color: #f8f9fa; padding: 10px; margin-bottom: 20px;">
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <div>
                <a href="${pageContext.request.contextPath}/" style="text-decoration: none; color: #333; font-weight: bold; font-size: 1.2em;">Библиотека</a>
            </div>
            <div>
                <%-- Проверяем, авторизован ли пользователь --%>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <%-- Если пользователь авторизован --%>
                        <span style="margin-right: 15px;">Добро пожаловать, ${sessionScope.user.username}!</span>
                        <a href="${pageContext.request.contextPath}/logout" style="margin-right: 10px;">Выйти</a>
                    </c:when>
                    <c:otherwise>
                        <%-- Если пользователь не авторизован --%>
                        <a href="${pageContext.request.contextPath}/login" style="margin-right: 15px;">Войти</a>
                        <a href="${pageContext.request.contextPath}/register" style="margin-right: 10px;">Регистрация</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</header>