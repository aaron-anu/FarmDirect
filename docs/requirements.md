# Software Requirements Specification (SRS) - FarmDirect

## 1. Introduction
### 1.1 Purpose
The FarmDirect system is a Java-based digital marketplace designed to connect farmers directly with buyers. The goal is to reduce dependence on intermediaries, ensure fair pricing for farmers, and provide fresh produce to consumers.

### 1.2 Scope
This micro project focuses on the core lifecycle of agricultural trade: User authentication, product listing management, and a shopping cart-based ordering system.

### 1.3 Technical Stack
- **Language:** Java
- **GUI Framework:** Java Swing (Classic)
- **Database:** MySQL
- **API:** JDBC (Java Database Connectivity)

---

## 2. User Roles & Permissions
The system implements Role-Based Access Control (RBAC):

| Role | Permissions | Key Actions |
| :--- | :--- | :--- |
| **Farmer** | Manage own produce | Add/Edit/Delete own products, View sales history. |
| **Buyer** | Purchase produce | Self-register, Browse catalog, Use shopping cart, View order history. |
| **Admin** | System Moderation | Manage all user accounts, Delete any product listing. |

---

## 3. Functional Requirements

### 3.1 User Management
- **Registration:** Buyers must be able to create their own accounts via a sign-up form.
- **Authentication:** A unified login screen that redirects users to their respective dashboards based on their role.

### 3.2 Product Management
- **Listing:** Farmers can add produce with Basic Details: `Product Name`, `Category`, `Price per kg`, and `Available Quantity`.
- **Catalog:** A central view (JTable) displaying all available products from all farmers.
- **Ownership:** Only the farmer who listed a product (or an Admin) can edit or delete that listing.

### 3.3 Ordering System
- **Shopping Cart:** Buyers can add multiple products to a virtual cart and checkout in a single transaction.
- **Payment Simulation:** A simple selection mechanism (UPI, Cash, or Card) to simulate the payment process.
- **Inventory Sync:** The system must automatically subtract the ordered quantity from the `products` table upon successful checkout.
- **Order Tracking:** Orders are tracked with a simple binary status: `Pending` or `Completed`.
- **History:** Both Buyers and Farmers must have access to a history of their past transactions.

---

## 4. Non-Functional Requirements
- **Usability:** The interface should be intuitive, utilizing a classic Java Swing layout for stability.
- **Data Integrity:** Use of Foreign Key constraints in MySQL to prevent orphaned records (e.g., an order for a product that doesn't exist).
- **Reliability:** Ensure that stock cannot be reduced below zero using database CHECK constraints.

---

## 5. System Flow (The Demo Loop)
1. **Farmer Login** $\rightarrow$ List "Organic Tomatoes" (100kg).
2. **Buyer Login** $\rightarrow$ Add "Organic Tomatoes" (10kg) to Cart $\rightarrow$ Checkout via UPI.
3. **System Action** $\rightarrow$ Create Order record $\rightarrow$ Update Tomato stock to 90kg.
4. **Verification** $\rightarrow$ Check Order History for Buyer and Sales History for Farmer.
