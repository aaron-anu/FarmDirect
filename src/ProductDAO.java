/**
 * Product Data Access Object with embedded Product model
 * 
 * Purpose: Handles all product CRUD operations (Create, Read, Update, Delete) for farmer inventory.
 * Manages database tables for listing products, stock updates, and category organization.
 */
public class ProductDAO {
    
    /**
     * Embedded Product Model Class
     * Represents a row in the products table with farmer relationship.
     * Used in product tables within buyer dashboard to display all available crops.
     */
    public static class Product {
        /** Primary key from products table */
        private int productId;
        
        /** Foreign key: user_id referencing User table's farmer accounts */
        private int farmerId;
        
        /** Name of the agricultural product (e.g., "Organic Tomatoes") */
        private String productName;
        
        /** Product category: Vegetable, Fruit, Dairy, Grain, or Meat */
        private String category;
        
        /** Price per kilogram in INR currency */
        private double pricePerKg;
        
        /** Available inventory quantity before deducting orders */
        private int availableQuantity;
        
        /**
         * Creates product from database SELECT query values
         * 
         * How it works: Receives productId, farmerId (FK), name, category enum, double price, and int stock.
         * Maps all 6 column values to instance fields for table display or order processing.
         */
        public Product(int productId, int farmerId, String name, String category, 
                       double pricePerKg, int availableQuantity) {
            // Set all 6 fields
        }
        
        /** Getters for each field used by GUI tables and order calculations */
        public int getProductId() { return productId; }
        public int getFarmerId() { return farmerId; }
        public String getProductName() { return productName; }
        public String getCategory() { return category; }
        public double getPricePerKg() { return pricePerKg; }
        public int getAvailableQuantity() { return availableQuantity; }
    }
    
    /**
     * Fetches product by its unique ID from database
     * 
     * How it works: Opens DB connection, executes SELECT * FROM products WHERE id=?.
     * Maps single ResultSet row to Product model object and returns null if no record found.
     * Used when farmer clicks edit/add button or buyer views specific crop details.
     */
    static Product getById(int productId) {
    }
    
    /**
     * Lists all products available for buyer dashboard display
     * 
     * How it works: Runs SELECT * FROM products ORDER BY productName DESC for alphabetical listing.
     * Maps entire ResultSet to List<Product> ArrayList, returns 0 items if table empty.
     * Called by buyer's "View Products" button in main GUI table panel.
     */
    static java.util.List<Product> getAllProducts() {
    }
    
    /**
     * Gets only farmer's own products for personal inventory management
     * 
     * How it works: Executes SELECT * FROM products WHERE farmer_id = ? with given userId.
     * Returns List<Product> containing only their crops, enabling "Add Product" form popup
     * to target specific farmer table section or admin moderation of all records.
     */
    static java.util.List<Product> getFarmerProducts(int farmerId) {
    }
    
    /**
     * Adds new product record to database via INSERT statement
     * 
     * How it works: Validates that productId is null (auto-increment), productName and quantity > 0,
     * constructs INSERT INTO products VALUES(?, ?, ?, ?, ?). Executes with prepared statement.
     * Returns true on success, false if foreign key farmer_id references non-existent user or stock=0.
     */
    static void addProduct(Product p) {
    }
    
    /**
     * Deletes product record from database
     * 
     * How it works: Executes DELETE FROM products WHERE id = ?, sets stock to 0 before delete,
     * clears GUI table rows after successful removal to show "product unlisted for sale" to buyers.
     */
    static void deleteProduct(int productId) {
    }
}