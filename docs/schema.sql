-- =============================================================================
-- FarmDirect - Finalized Database Schema (Micro Project)
-- Focus: Balanced complexity - User Auth, Product Listings, and Orders.
-- =============================================================================

CREATE DATABASE IF NOT EXISTS farmdirect_final;
USE farmdirect_final;

-- -----------------------------------------------------------------------------
-- TABLE 1: users
-- Purpose: Handles authentication and role-based access (Farmer/Buyer).
-- -----------------------------------------------------------------------------
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    role ENUM('Farmer', 'Buyer', 'Admin') NOT NULL,
    full_name VARCHAR(100),
    contact VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- -----------------------------------------------------------------------------
-- TABLE 2: products
-- Purpose: Stores produce listed by farmers. Linked to the user who listed it.
-- -----------------------------------------------------------------------------
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    farmer_id INT NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price_per_kg DECIMAL(10, 2) NOT NULL,
    available_quantity INT NOT NULL CHECK (available_quantity >= 0),
    listing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (farmer_id) REFERENCES users(user_id) 
        ON DELETE CASCADE
);

-- -----------------------------------------------------------------------------
-- TABLE 3: orders
-- Purpose: Tracks purchases. Links the buyer, the product, and the farmer.
-- -----------------------------------------------------------------------------
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    buyer_id INT NOT NULL,
    order_qty INT NOT NULL CHECK (order_qty > 0),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2),
    
    FOREIGN KEY (product_id) REFERENCES products(product_id) 
        ON DELETE CASCADE,
    FOREIGN KEY (buyer_id) REFERENCES users(user_id) 
        ON DELETE CASCADE
);

-- =============================================================================
-- SAMPLE DATA FOR IMMEDIATE DEMO
-- =============================================================================

-- Create Users (Farmers and Buyers)
INSERT INTO users (username, password, role, full_name, contact) VALUES
('farmer1', 'pass123', 'Farmer', 'Ram Kumar', '9876543210'),
('farmer2', 'pass123', 'Farmer', 'Sita Devi', '9876543211'),
('buyer1', 'pass123', 'Buyer', 'John Doe', '9876543212'),
('buyer2', 'pass123', 'Buyer', 'Jane Smith', '9876543213'),
('admin', 'admin123', 'Admin', 'System Admin', '0000000000');

-- Create Products (Linked to farmer_id 1 and 2)
INSERT INTO products (farmer_id, product_name, category, price_per_kg, available_quantity) VALUES
(1, 'Organic Tomatoes', 'Vegetable', 40.00, 100),
(1, 'Green Chilies', 'Vegetable', 60.00, 50),
(2, 'Alphonso Mangoes', 'Fruit', 150.00, 30),
(2, 'Fresh Bananas', 'Fruit', 30.00, 120);

-- Create Sample Orders
INSERT INTO orders (product_id, buyer_id, order_qty, total_amount) VALUES
(1, 3, 5, 200.00),
(3, 4, 2, 300.00);

-- =============================================================================
-- END OF SCHEMA
-- =============================================================================
