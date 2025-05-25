package Library.web.models;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role; // "ADMIN", "USER"

    public User() {}

    public User(String username, String passwordHash, String email, String role) {
        this.username = username;
        this.password = passwordHash;
        this.email = email;
        this.role = role;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    // Сеттеры
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}