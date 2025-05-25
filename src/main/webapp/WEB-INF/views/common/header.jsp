<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav style="background: #f8f9fa; padding: 10px 20px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
        <div style="display: flex; justify-content: space-between; align-items: center; max-width: 1200px; margin: 0 auto;">
            <!-- Логотип/название -->
            <div>
                <a href="${pageContext.request.contextPath}/" style="text-decoration: none; color: #333; font-weight: bold; font-size: 1.2em;">
                    Библиотека
                </a>
            </div>

            <!-- Блок авторизации -->
            <div style="display: flex; align-items: center; gap: 15px;">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <!-- Если пользователь авторизован -->
                        <span style="font-weight: 500;">${sessionScope.user.username}</span>
                        <a href="${pageContext.request.contextPath}/logout"
                           style="padding: 5px 10px; background: #dc3545; color: white; border-radius: 4px; text-decoration: none;">
                            Выйти
                        </a>
                    </c:when>
                    <c:otherwise>
                        <!-- Если пользователь не авторизован - форма входа -->
                        <form action="${pageContext.request.contextPath}/login" method="post"
                              style="display: flex; gap: 10px; align-items: center;">
                            <input type="text" name="username" placeholder="Логин"
                                   style="padding: 5px; border: 1px solid #ddd; border-radius: 4px;">
                            <input type="password" name="password" placeholder="Пароль"
                                   style="padding: 5px; border: 1px solid #ddd; border-radius: 4px;">
                            <button type="submit"
                                    style="padding: 5px 15px; background: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer;">
                                Войти
                            </button>
                        </form>
                        <a href="${pageContext.request.contextPath}/register"
                           style="padding: 5px 10px; background: #007bff; color: white; border-radius: 4px; text-decoration: none;">
                            Регистрация
                        </a>
                        <c:when test="${not empty sessionScope.user}">
                            <!-- Если пользователь авторизован -->
                            <span style="font-weight: 500;">${sessionScope.user.username}</span>

                            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                                <a href="${pageContext.request.contextPath}/users"
                                   style="margin-right: 10px; padding: 5px 10px; background: #6c757d; color: white; border-radius: 4px; text-decoration: none;">
                                    Пользователи
                                </a>
                            </c:if>

                            <a href="${pageContext.request.contextPath}/logout"
                               style="padding: 5px 10px; background: #dc3545; color: white; border-radius: 4px; text-decoration: none;">
                                Выйти
                            </a>
                        </c:when>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</header>