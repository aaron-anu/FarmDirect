import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// Product Data Access Object with embedded Product model
public class ProductDAO {
    public static class Product {
        private int productId;
        private int farmerId;
        private String productName;
        private String category;
        private double pricePerKg;
        private int availableQuantity;

        public Product(int productId, int farmerId, String name, String category,
                       double pricePerKg, int availableQuantity) {
            this.productId = productId;
            this.farmerId = farmerId;
            this.productName = name;
            this.category = category;
            this.pricePerKg = pricePerKg;
            this.availableQuantity = availableQuantity;
        }

        public int getProductId() { return productId; }
        public int getFarmerId() { return farmerId; }
        public String getProductName() { return productName; }
        public String getCategory() { return category; }
        public double getPricePerKg() { return pricePerKg; }
        public int getAvailableQuantity() { return availableQuantity; }
    }

    static Product getById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProduct(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY product_name DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    static List<Product> getFarmerProducts(int farmerId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE farmer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, farmerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    products.add(mapResultSetToProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    static boolean addProduct(Product p) {
        if (p.getProductName() == null || p.getProductName().isEmpty() || p.getAvailableQuantity() <= 0) {
            return false;
        }

        String sql = "INSERT INTO products (farmer_id, product_name, category, price_per_kg, available_quantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, p.getFarmerId());
            pstmt.setString(2, p.getProductName());
            pstmt.setString(3, p.getCategory());
            pstmt.setDouble(4, p.getPricePerKg());
            pstmt.setInt(5, p.getAvailableQuantity());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt("product_id"),
            rs.getInt("farmer_id"),
            rs.getString("product_name"),
            rs.getString("category"),
            rs.getDouble("price_per_kg"),
            rs.getInt("available_quantity")
        );
    }
}
