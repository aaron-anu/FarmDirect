import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/farmdirect";
    private static final String USER = "mysql";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection to Server Failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
