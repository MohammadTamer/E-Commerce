# 🛒 E-Commerce Java Project

This is a simple console-based E-Commerce application written in Java. It supports product management, shopping cart functionality, customer balance checking, and basic exception handling.

## 📁 Project Structure
E-Commerce/
├── exceptions/ # Custom exception classes
│ ├── EmptyCartException.java
│ ├── ExpiredProductException.java
│ ├── InsufficientBalanceException.java
│ └── OutOfStockException.java
├── Cart.java # Manages cart operations
├── Customer.java # Represents a customer
├── Item.java # Interface for common item behavior
├── Product.java # Base product class
├── expirationProducts.java # Products with expiration dates
├── shippableProducts.java # Products that can be shipped
├── Shippable.java # Interface for shipping behavior
├── Main.java # Main entry point
└── untitled1.iml # IntelliJ project file


## ✅ Features

- Add/remove products from cart
- Expiration check for products
- Shipping support for certain items
- Balance checking during checkout
- Custom exception handling:
  - EmptyCartException
  - ExpiredProductException
  - InsufficientBalanceException
  - OutOfStockException

## 🚀 How to Run

1. Open the project in IntelliJ IDEA.
2. Build the project (`Build > Build Project`).
3. Run `Main.java`.

## 🛠 Technologies

- Java 17+ (or compatible version)
- IntelliJ IDEA

## 📌 Notes

- All logic is handled through the console.
- You can extend this to a GUI or database-backed version in the future.

---

Feel free to customize this `README.md` with your name or GitHub info if you plan to publish it!

Want me to generate the actual file content for download?
