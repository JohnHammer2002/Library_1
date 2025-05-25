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

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        HttpSession session = request.getSession(false);

        try {
            // Проверяем права администратора
            if (session != null && "ADMIN".equals(((User)session.getAttribute("user")).getRole())) {
                boolean deleted = userDao.deleteUser(userId);

                if (deleted) {
                    // Если пользователь удалил сам себя - завершаем сессию
                    if (userId == ((User)session.getAttribute("user")).getId()) {
                        session.invalidate();
                        response.sendRedirect(request.getContextPath() + "/?message=Ваш аккаунт был удален");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/users?message=Пользователь успешно удален");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/users?error=Не удалось удалить пользователя");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/?error=Недостаточно прав");
            }
        } catch (SQLException e) {
            throw new ServletException("Ошибка при удалении пользователя", e);
        }
    }
}