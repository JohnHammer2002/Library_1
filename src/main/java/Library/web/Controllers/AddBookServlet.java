package Library.web.Controllers;

import Library.web.DAO.BookDAO;
import Library.web.models.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-book")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Book book = new Book();
            book.setName(req.getParameter("name"));
            book.setAuthor(req.getParameter("author"));
            book.setPublisher(req.getParameter("publisher"));

            String pageNumberStr = req.getParameter("pageNumber");
            if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
                book.setPageNumber(Integer.parseInt(pageNumberStr));
            }

            book.setDescription(req.getParameter("description"));

            BookDAO.addBook(book);
            resp.sendRedirect(req.getContextPath() + "/books");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректное число страниц");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Ошибка при добавлении книги: " + e.getMessage());
        }
    }
}
