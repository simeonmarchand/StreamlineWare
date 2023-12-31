package com.simeonmarchand.streamlineware.data;

import com.simeonmarchand.streamlineware.data.Item;
import com.simeonmarchand.streamlineware.logger.InventoryLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ItemDAO {

    public static final Logger logger = Logger.getLogger("CustomLogger");
    public boolean addItem(Connection connection, Item item) {
        // validate the input
        if (item.getName().isEmpty() || item.getCategory().isEmpty() || item.getDescription().isEmpty() ||
                item.getUnitPrice() == null || item.getQuantityInStock() <= 0 || item.getMinimumStockLevel() < 0) {
            // warning logger
            InventoryLogger.logError("Failed to add item to database: One or more required fields are missing.");
            return false;
        }

        // Insert an item into the database
        String query = "INSERT INTO items (name, category, description, unit_price, quantity_in_stock, minimum_stock_level) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // set the parameters in the prepared statement
            statement.setString(1, item.getName());
            statement.setString(2, item.getCategory());
            statement.setString(3, item.getDescription());
            statement.setBigDecimal(4, item.getUnitPrice());
            statement.setInt(5, item.getQuantityInStock());
            statement.setInt(6, item.getMinimumStockLevel());

            //execute the query and check if it was successful
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                InventoryLogger.logInfo("Product Item " + item.getName() + " was inserted");
                return true;
            } else {
                InventoryLogger.logError("Product Item " + item.getName() + " was not inserted");
            }
            // returns true if at least one row was inserted
        } catch (SQLException exception) {
            InventoryLogger.logError("An SQL error occurred while inserting item " + item.getName() + " into the database");
            exception.printStackTrace();
        }
        // returns false if no rows were inserted
        return false;
    }

    // Other CRUD methods can be similarly implemented here

    // delete item from database method
    public boolean deleteItem(Connection connection, Item item){


        return false;
    }


    // logger methods
}
