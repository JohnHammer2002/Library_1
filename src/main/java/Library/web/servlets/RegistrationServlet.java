package Library.web.servlets;

import Library.web.dao.UserDao;
import Library.web.models.User;
import Library.web.utils.PasswordHasher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Отображаем форму регистрации
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем данные из формы
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        // Валидация данных
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                email == null || email.isEmpty()) {
            req.setAttribute("errorMessage", "Все поля должны быть заполнены");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        try {
            // Проверяем, существует ли пользователь с таким именем
            if (userDao.getUserByUsername(username) != null) {
                req.setAttribute("errorMessage", "Пользователь с таким именем уже существует");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
                return;
            }

            // Хешируем пароль перед сохранением
            String hashedPassword = PasswordHasher.hashPassword(password);

            // Создаем нового пользователя с ролью USER по умолчанию
            User newUser = new User(username, hashedPassword, email, "USER");

            // Сохраняем пользователя в базу данных
            userDao.insertUser(newUser);

            // Перенаправляем на страницу успешной регистрации
            resp.sendRedirect(req.getContextPath() + "/register-success");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Ошибка при регистрации: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}