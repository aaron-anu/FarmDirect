import java.sql.*;
import java.util.*;

// Order Data Access Object with embedded Order model
public class OrderDAO {
    public static class Order {
        private int orderId;
        private int buyerId;
        private int productId;
        private int orderQty;
        private String paymentMethod;

        public Order(int orderId, int buyerId, int productId, int orderQty, String paymentMethod) {
            this.orderId = orderId;
            this.buyerId = buyerId;
            this.productId = productId;
            this.orderQty = orderQty;
            this.paymentMethod = paymentMethod;
        }

        public int getOrderId() { return orderId; }
        public int getBuyerId() { return buyerId; }
        public int getProductId() { return productId; }
        public int getOrderQty() { return orderQty; }
        public String getPaymentMethod() { return paymentMethod; }
    }

    static void placeOrder(Order order, java.sql.Connection conn) {
        double pricePerKg = 0.0;

        try {
            String priceSql = "SELECT price_per_kg FROM products WHERE product_id = ?";
            PreparedStatement priceStmt = conn.prepareStatement(priceSql);
            priceStmt.setInt(1, order.getProductId());
            ResultSet rs = priceStmt.executeQuery();

            if (rs.next()) {
                pricePerKg = rs.getDouble("price_per_kg");
            } else {
                System.out.println("Error: Product not found!");
                return;
            }

            double totalAmount = pricePerKg * order.getOrderQty();
            String insertSql = "INSERT INTO orders (product_id, buyer_id, order_qty, total_amount) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setInt(1, order.getProductId());
            insertStmt.setInt(2, order.getBuyerId());
            insertStmt.setInt(3, order.getOrderQty());
            insertStmt.setDouble(4, totalAmount);
            insertStmt.executeUpdate();

            String updateSql = "UPDATE products SET available_quantity = available_quantity - ? WHERE product_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, order.getOrderQty());
            updateStmt.setInt(2, order.getProductId());
            updateStmt.executeUpdate();

            System.out.println("Order placed successfully! Total: " + totalAmount);

        } catch (java.sql.SQLException e) {
            System.out.println("Order placement failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static Order getOrderById(int orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Order( rs.getInt("order_id"), rs.getInt("buyer_id"), rs.getInt("product_id"), rs.getInt("order_qty"), "N/A"  );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static List<Order> getCartItems(String buyerUsername) {
        List<Order> orderList = new ArrayList<>();
               String sql = "SELECT o.* FROM orders o JOIN users u ON o.buyer_id = u.user_id WHERE u.username = ?";

               try (Connection conn = DBConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {

                   stmt.setString(1, buyerUsername);
                   ResultSet rs = stmt.executeQuery();

                   while (rs.next()) {
                       orderList.add(new Order(
                           rs.getInt("order_id"),
                           rs.getInt("buyer_id"),
                           rs.getInt("product_id"),
                           rs.getInt("order_qty"),
                           "N/A"
                       ));
                   }
               } catch (SQLException e) {
                   System.out.println("Error fetching cart items: " + e.getMessage());
                   e.printStackTrace();
               }
               return orderList;
    }

    static Order completeOrder(int orderId, String status) {
        System.out.println("Order ID " + orderId + " has been marked as: " + status);
        return getOrderById(orderId);
    }
}
