package Library.web.Servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/main")  // URL-путь к сервлету
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.setAttribute("pageTitle", "Динамический заголовок");
        req.getRequestDispatcher("views/page.jsp").forward(req, resp);
    }

    public static class DatabaseConnection {
        private static final HikariDataSource dataSource;

        static {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/catalog");
            config.setUsername("root");
            config.setPassword("Ivanm01022002/");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            dataSource = new HikariDataSource(config);
        }

        public static Connection getConnection() throws SQLException {
            return dataSource.getConnection();
        }
    }
}

