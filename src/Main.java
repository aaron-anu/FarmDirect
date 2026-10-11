/**
 * Entry point for FarmDirect Swing application
 */
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        // Initialize database connection singleton
        DBConnection.getConnection();
        
        // Launch Swing event dispatch thread
        SwingUtilities.invokeLater(() -> new FarmDirectGUI().setVisible(true));
        
        System.out.println("FarmDirect Application Started");
    }
}