/**
 * FarmDirect Swing GUI Application
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FarmDirectGUI {
    
    private JFrame frame;
    private JCardLayout cardLayout;
    private DefaultTableModel productTable;
    private DefaultTableModel cartTable;
    
    // GUI Components
    private JLabel welcomeLabel;
    private JButton loginBtn, logoutBtn;
    private JButton addProductBtn, editItemBtn, deleteItemBtn;
    private JTable productTablePanel;
    private JSpinner priceSpinner;
    private JButton placeOrderBtn;
    
    /**
     * Launch Swing application entry point
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FarmDirect Market");
            FarmDirectGUI gui = new FarmDirectGUI(frame);
            // Switch cards based on user role
        });
    }
    
    public FarmDirectGUI(JFrame parentFrame) {
        // Initialize CardLayout panels: LOGIN, DASHBOARD, ADMIN HISTORY
    }
    
    private void loadLoginPanel() {
        // Create username/password fields and login button
    }
    
    private void loadDashboard(User user) {
        // Render Buyer/Farmer/Admin-specific dashboard sections
    }
    
    private void switchToCartView(Product p) {
        // Populate product table with cart items for checkout
    }
    
    private void updateProductTable(ArrayList<Product> products) {
        // Refresh buyer's visible product list
    }
    
    private boolean checkStockLevel(int productId, int quantity) {
        // Verify available stock before order placement
    }
}