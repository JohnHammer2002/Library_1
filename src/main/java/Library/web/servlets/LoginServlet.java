package Library.web.servlets;

import Library.web.dao.UserDao;
import Library.web.models.User;
import Library.web.utils.PasswordHasher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Перенаправляем на страницу входа, если запрос пришел не из шапки
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String redirectUrl = request.getParameter("redirect");

        // Если параметры не пришли из формы (например, из шапки)
        if (username == null || password == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            User user = userDao.getUserByUsername(username);

            if (user != null && PasswordHasher.verifyPassword(password, user.getPassword())) {
                // Создаем сессию и сохраняем пользователя
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Перенаправляем на защищенную страницу или туда, откуда пришли
                if (redirectUrl != null && !redirectUrl.isEmpty()) {
                    response.sendRedirect(redirectUrl);
                } else {
                    response.sendRedirect(request.getContextPath() + "/");
                }
            } else {
                // Если авторизация не удалась
                request.setAttribute("error", "Неверный логин или пароль");

                // Если запрос пришел из шапки, перенаправляем на страницу входа
                if (request.getHeader("X-Requested-With") == null) {
                    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                } else {
                    // Для AJAX-запросов (если потребуется)
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Неверный логин или пароль");
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Ошибка при работе с базой данных", e);
        }
    }
}