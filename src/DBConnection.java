import java.sql.*;

public class DBConnection {
    private static String URL = "jdbc:mysql://localhost:3306/farmdirect";
    private static String USER = "mysql";
    private static String PASSWORD = "password";
    private static Connection conn;

    public static Connection getConnection() {
        // Class.forName("com.mysql.cj.jdbc.Driver");
        if (conn == null)
            try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (Exception e) {
                System.out.println("Connection to Server Failed");
            }
        return conn;
    }
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Closing Connection to Server Failed");
            }
        }
    }
}
