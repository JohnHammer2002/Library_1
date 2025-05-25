package Library.web.dao;

import Library.web.models.Book;
import Library.web.utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    // SQL queries
    private static final String INSERT_BOOK =
            "INSERT INTO books (name, author, publisher, page_number, description, image_url) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BOOK_BY_ID =
            "SELECT id, name, author, publisher, page_number, description, image_url FROM books WHERE id = ?";
    private static final String SELECT_ALL_BOOKS =
            "SELECT id, name, author, publisher, page_number, description, image_url FROM books";
    private static final String DELETE_BOOK =
            "DELETE FROM books WHERE id = ?";
    private static final String UPDATE_BOOK =
            "UPDATE books SET name = ?, author = ?, publisher = ?, page_number = ?, " +
                    "description = ?, image_url = ? WHERE id = ?";
    private static final String SEARCH_BOOKS =
            "SELECT id, name, author, publisher, page_number, description, image_url FROM books " +
                    "WHERE name LIKE ? OR author LIKE ? OR publisher LIKE ?";

    // Create a new book
    public boolean createBook(Book book) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_BOOK, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getPageNumber());
            stmt.setString(5, book.getDescription());
            stmt.setString(6, book.getImageUrl());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get book by ID
    public Book getBookById(int id) {
        Book book = null;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BOOK_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPageNumber(rs.getInt("page_number"));
                book.setDescription(rs.getString("description"));
                book.setImageUrl(rs.getString("image_url"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    // Get all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_BOOKS)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPageNumber(rs.getInt("page_number"));
                book.setDescription(rs.getString("description"));
                book.setImageUrl(rs.getString("image_url"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    // Update book
    public boolean updateBook(Book book) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_BOOK)) {

            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getPageNumber());
            stmt.setString(5, book.getDescription());
            stmt.setString(6, book.getImageUrl());
            stmt.setInt(7, book.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete book
    public boolean deleteBook(int id) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_BOOK)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search books by name, author or publisher
    public List<Book> searchBooks(String searchTerm) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SEARCH_BOOKS)) {

            String likeTerm = "%" + searchTerm + "%";
            stmt.setString(1, likeTerm);
            stmt.setString(2, likeTerm);
            stmt.setString(3, likeTerm);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPageNumber(rs.getInt("page_number"));
                book.setDescription(rs.getString("description"));
                book.setImageUrl(rs.getString("image_url"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}