package Library.web.DAO;

import Library.web.Controllers.MainServlet;
import Library.web.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    //CREATE
    public static void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (name, author, publisher, page_number, description) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MainServlet.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getPageNumber());
            stmt.setString(5, book.getDescription());

            stmt.executeUpdate();

            // Получаем сгенерированный ID
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    book.setId(rs.getInt(1));
                }
            }
        }
    }

    //READ
    public static List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = MainServlet.DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(mapRowToBook(rs));
            }
        }
        return books;
    }

    public static Book getBookById(int id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection conn = MainServlet.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBook(rs);
                }
            }
        }
        return null;
    }

    //UPDATE
    public static void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET name = ?, author = ?, publisher = ?, " +
                "page_number = ?, description = ? WHERE id = ?";

        try (Connection conn = MainServlet.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getPageNumber());
            stmt.setString(5, book.getDescription());
            stmt.setInt(6, book.getId());
            stmt.executeUpdate();
        }
    }

    //DELETE
    public static void deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = MainServlet.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Вспомогательный метод для преобразования ResultSet в Book
    private static Book mapRowToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setPublisher(rs.getString("publisher"));
        book.setPageNumber(rs.getInt("page_number"));
        book.setDescription(rs.getString("description"));
        return book;
    }
}
