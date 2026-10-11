/**
 * User Data Access Object with embedded User model
 * 
 * Purpose: Handles user login, registration, profile retrieval, and dashboard display.
 * Contains an inner User class for passing user data between GUI and DAO layers.
 */
public class UserDAO {
    
    /**
     * Embedded User Model Class
     * Represents a user record from the users table.
     * Used to pass login sessions or retrieved profiles between methods.
     */
    public static class User {
        /** Unique identifier for each user (PK) */
        private int userId;
        
        /** Username for login authentication */
        private String username;
        
        /** Full name display field */
        private String fullName;
        
        /** Role ENUM: "Farmer", "Buyer", or "Admin" */
        private String role;
        
        /**
         * Creates a new User instance populated from database query result
         * 
         * How it works: Receives raw int and String values, assigns to this fields.
         * Called by login() after executing SELECT on users table, or registration
         * INSERT confirmation to display welcome message.
         */
        public User(int userId, String username, String fullName, String role) {
            // Initialize all 4 fields with provided values
        }
        
        /** Getters for all fields - used by GUI to display user data */
        public int getUserId() { return userId; }
        public String getUsername() { return username; }
        public String getFullName() { return fullName; }
        public String getRole() { return role; }
    }
    
    /**
     * Authenticates a user by checking username and password
     * 
     * How it works: Opens DB connection via DBConnection.getConnection(), runs
     * SELECT query on users table WHERE username=? AND password=?. If record found,
     * maps ResultSet to new User(userId, username, fullName, role). Closes connection
     * before returning. Returns null on failed authentication attempt.
     * 
     * Why returns User object: Passed directly from login() call in GUI main method
     * to Dashboard panel showing farmer products/buyer cart/admin moderation tools based on role.
     */
    static User login(String username, String password) {
    }
    
    /**
     * Registers a new user account into the DB
     * 
     * How it works: Opens connection, checks if username already exists (UNIQUE constraint),
     * validates input strings are not empty, executes INSERT INTO users VALUES (?, ?, ?),
     * returns true if insert succeeded, false on duplicate or validation failure. No User object
     * returned since registration doesn't provide session - use login() after success instead.
     */
    static boolean register(String username, String fullName, String role) {
    }
    
    /**
     * Retrieves active user session by username lookup
     * 
     * How it works: Runs SELECT on users WHERE username=? to fetch existing credentials.
     * Maps result set to User model object and returns populated instance for dashboard display.
     * Used after successful login to avoid code repeating credential verification logic.
     */
    static User listByUsername(String username) {
    }
    
    /**
     * Displays user profile section based on role permissions
     * 
     * How it works: Exposes getFullName() for welcome message, calls role getter for conditional
     * rendering. For "Farmer" role invokes ProductDAO.getFarmerProducts(), "Buyer" shows cart view,
     * "Admin" enables all tables including delete product buttons. Avoids showing admin menus to buyers/farmers.
     */
    static void showUserProfile(User user) {
    }
}