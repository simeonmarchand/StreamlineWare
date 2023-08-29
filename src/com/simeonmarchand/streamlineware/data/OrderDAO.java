package com.simeonmarchand.streamlineware.data;

import com.simeonmarchand.streamlineware.logger.InventoryLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

    public boolean orderItem(Connection connection, Item item, OrderDAO order) {
        int quantityOrdered = order.getQuantityOrdered();

        if (item.getQuantityInStock() < quantityOrdered){
            // Warning: Not enough stock to fulfill order
            InventoryLogger.logInfo("Not enough stock to fulfill order");
            return false;
        }

        // calculate new quantity in stock
        int newQuantityInStock = item.getQuantityInStock() - quantityOrdered;

        // update the database
        String query = "UPDATE items SET quantity_in_stock = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, newQuantityInStock);
            statement.setInt(2, item.getItemID());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0 ){
                InventoryLogger.logInfo("Product Item: "+ item.getName() + " has been updated");
                return true;
            } else {
                InventoryLogger.logInfo("Product Item: "+ item.getName() + " has not been updated");
                return false;
            }
        } catch (SQLException e){
            InventoryLogger.logError("Failed to update product item: " + item.getName());
            e.printStackTrace();
        }
        return false;
    }

    private int getQuantityOrdered() {
        return 0;
    }

    public void updateItemQuantity(Item item) {
        //TODO: implement this method to update the item quantity in the database
        int newQuantityInStock = item.getQuantityInStock() - getQuantityOrdered();
        String query = "UPDATE items SET quantity_in_stock = ? WHERE id = ?";

    }
}
