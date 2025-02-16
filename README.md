# eCommerce store

## Overview
This assignment requires you to create a program that simulates an eCommerce store. You will implement classes to represent different types of items sold in the store and the customers purchasing them. Additionally, you will generate various reports based on sales and inventory data.

## Classes
Your program should include the following classes:

### 1. Item (Base Class)
Represents an item sold in the store. Properties:
- `name`: String representing the name of the item.
- `price`: Float representing the price of the item.
- `quantity`: Integer representing the number of items in stock.
- `category`: String representing the item's category (e.g., "clothing", "electronics").

### 2. Customer
Represents a customer who purchases items. Properties:
- `name`: String representing the customer's name.
- `email`: String representing the customer's email address.
- `purchases`: List of `Purchase` objects representing the customer's purchases.

### 3. Purchase
Represents an item purchased by a customer. Properties:
- `item`: `Item` object representing the purchased item.
- `quantity`: Integer representing the number of items purchased.

## Reports
Each report should be implemented as a separate static class exposing a `generate()` method:

1. **TopSellingItemsReport**: Displays the top 10 selling items, sorted by quantity sold in descending order.
2. **TopCustomersReport**: Displays the top 10 customers, sorted by total amount spent in descending order.
3. **CategorySalesReport**: Displays total sales for each category, sorted by total sales in descending order.
4. **CustomerPurchaseHistoryReport**: Displays purchase history for a given customer, including item names and total amount spent on each item.
5. **InventoryReport**: Displays current inventory levels for all items, sorted by quantity in stock in descending order.

## Implementation Details
- Each class should be well-structured and follow object-oriented principles.
- Reports should be implemented as static classes with a `generate()` method.
- Unit tests should be written to cover all classes and their functionality.

## How to Run
```sh
# Clone the repository
git clone https://github.com/your-username/eCommerce-store.git
cd eCommerce-store

# Compile and run the program
javac Main.java
java Main
```

## Future Enhancements
- Add support for additional item categories and customer types.
- Implement a graphical user interface (GUI) for improved usability.
- Integrate with a database for persistent storage.

