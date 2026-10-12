import java.sql.*;
// User Data Access Object with embedded User model
public class UserDAO {

    public static class User {
        private int userId;
        private String username;
        private String fullName;
        private String role;

        public User(int userId, String username, String fullName, String role) {
            this.userId = userId;
            this.username = username;
            this.fullName = fullName;
            this.role = role;
        }

        public int getUserId() { return userId; }
        public String getUsername() { return username; }
        public String getFullName() { return fullName; }
        public String getRole() { return role; }
    }

    static User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("fullName"),
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Registers a new user account into the DB
     */
    static boolean register(String username, String fullName, String role) {
        if (username == null || username.isEmpty() || fullName == null || fullName.isEmpty()) {
            return false;
        }

        String sql = "INSERT INTO users (username, fullName, role) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, fullName);
            pstmt.setString(3, role);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // This will catch UNIQUE constraint violations (duplicate usernames)
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves active user session by username lookup
     */
    static User listByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("fullName"),
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void showUserProfile(User user) {
        if (user == null) return;

        System.out.println("Welcome, " + user.getFullName());

        String role = user.getRole();
        if ("Farmer".equalsIgnoreCase(role)) {
            var products = ProductDAO.getFarmerProducts(user.getUserId());
            System.out.println("Loading your inventory: " + products.size() + " products found.");
        } else if ("Buyer".equalsIgnoreCase(role)) {
            System.out.println("Loading Marketplace view...");
        } else if ("Admin".equalsIgnoreCase(role)) {
            System.out.println("Loading Administration Panel...");
        }
    }
}
