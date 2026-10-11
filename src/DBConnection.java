/**
 * Singleton Database Connection Utility
 * 
 * Purpose: Provides lazy-initialized MySQL connection for all DAO operations.
 * 
 */
public class DBConnection {
    
    /** Database URL for local MySQL server */
    private static String URL = "jdbc:mysql://localhost:3306/farmdirect";
    
    /** MySQL username */
    private static String USER = "root";
    
    /** MySQL password */
    private static String PASSWORD = "";
    
    /** Singleton connection holder (initialized lazily on first call) */
    private static java.sql.Connection conn;
    
    /**
     * Get active database connection instance
     * 
     * How it works: Implements lazy initialization pattern -
     * checks if conn is null, then creates new connection via DriverManager,
     * stores in static field and returns. This avoids opening multiple
     * connections simultaneously across your 6 files.
     */
    public static java.sql.Connection getConnection() {
    }
    
    /**
     * Close the singleton connection when application terminates
     * 
     * How it works: Checks if conn exists, closes it safely using try/catch
     * to handle any SQLExceptions, sets to null to clear reference before
     * JVM shutdown completes naturally.
     */
    public static void closeConnection() {
    }
}