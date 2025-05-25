package Library.web.servlets;

import Library.web.dao.UserDao;
import Library.web.models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Проверяем права администратора
        if (session != null && "ADMIN".equals(((User)session.getAttribute("user")).getRole())) {
            try {
                List<User> users = userDao.selectAllUsers();
                request.setAttribute("users", users);
                request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Ошибка при получении списка пользователей", e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/?error=Недостаточно прав");
        }
    }
}