package com.simeonmarchand.streamlineware.ui;

import com.simeonmarchand.streamlineware.data.DatabaseConnection;
import com.simeonmarchand.streamlineware.logger.InventoryLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderForm extends JFrame {
    private JTextField itemnameField;
    private JTextField quantityField;
    private JTextField orderDateField;
    private JTextField customerNameField;
    private JTextField customerEmailField;
    private JTextField orderNotesField;
    private int requestedQuantity;
    
    public PlaceOrderForm(){
        setTitle("Place Order");
        setSize(500, 500);
        
        JPanel orderPanel = new JPanel(new GridLayout(8,2, 10, 10));
        orderPanel.add(new JLabel("Item Name:"));
        itemnameField = new JTextField();
        orderPanel.add(itemnameField);
        orderPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        orderPanel.add(quantityField);
        orderPanel.add(new JLabel("Order Date:"));
        orderDateField = new JTextField();
        orderPanel.add(orderDateField);
        orderPanel.add(new JLabel("Customer Name:"));
        customerNameField = new JTextField();
        orderPanel.add(customerNameField);
        orderPanel.add(new JLabel("Customer Email:"));
        customerEmailField = new JTextField();
        orderPanel.add(customerEmailField);
        orderPanel.add(new JLabel("Order Notes:"));
        orderNotesField = new JTextField();
        orderPanel.add(orderNotesField);
        
        JButton placeOrderButton = new JButton("Place Order");
        orderPanel.add(new JLabel());
        orderPanel.add(placeOrderButton);
        
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement the place order functionality
                // retrieve the input values, update the database, and add the order
                // display a success/failure message
                
                // retrieve the input values
                String itemName = itemnameField.getText();
                String quantity = quantityField.getText();
                String orderDate = orderDateField.getText();
                String customerName = customerNameField.getText();
                String customerEmail = customerEmailField.getText();
                String orderNotes = orderNotesField.getText();
                
                // log the input values
                if (itemName.isEmpty()){
                    InventoryLogger.logError("Item Name is empty");
                } else
                    InventoryLogger.logInfo("This information is being logged\n" + " Item Name: " + itemName +
                        " Quantity: \n" + quantity + " Order Date: \n" + orderDate + " Customer Name: \n" + customerName +
                        "Customer Email: \n" + customerEmail + " Order Notes \n" + orderNotes);
                
                // update the database
                // add the order
                // display a success/failure message
                String checkStockQuery = "SELECT quantity_in_stock FROM items WHERE name = ?";
                
                try (Connection connection = DatabaseConnection.getConnection()) {
                    PreparedStatement checkStockStatement = connection.prepareStatement(checkStockQuery);
                    checkStockStatement.setString(1, itemName);
                    ResultSet stockResult = checkStockStatement.executeQuery();
                    
                    if (stockResult.next()) {
                        requestedQuantity = Integer.parseInt(quantity);
                        int currentStock = stockResult.getInt("quantity_in_stock");
                        
                        if (currentStock >= requestedQuantity){
                            String getItemIDQuery = "SELECT items_id FROM items WHERE name = ?";
                            String updateStockQuery = "UPDATE items SET quantity_in_stock = ? WHERE name = ?";
                            String insertOrderQuery = "INSERT INTO orders (items_id, quantity_ordered, order_date, " +
                                    // I
                                    // believe that the issue is with the insert order query
                                    "customer_name, customer_email, order_notes) VALUES (?, ?, ?, ?, ?, ?)";
                            
                            try(PreparedStatement getItemIDStatement = connection.prepareStatement(getItemIDQuery);
                                PreparedStatement updateStockStatement = connection.prepareStatement(updateStockQuery);
                                PreparedStatement insertOrderStatement = connection.prepareStatement(insertOrderQuery)){
                                
                                // get the item ID
                                getItemIDStatement.setString(1, itemName);
                                ResultSet itemIDResult = getItemIDStatement.executeQuery();
                                
                                if (itemIDResult.next()){
                                    int itemID = itemIDResult.getInt("items_id");
                                    System.out.println("Item ID: " + itemID);
                                
                                
                                // update the stock
                                System.out.println("Current Stock: " + currentStock);
                                System.out.println("Requested Quantity: " + requestedQuantity);
                                int newStock = currentStock - requestedQuantity;
                                System.out.println("New Stock: " + newStock);
                                updateStockStatement.setInt(1, newStock);
                                updateStockStatement.setString(2, itemName);
                                updateStockStatement.executeUpdate();
                                InventoryLogger.logInfo("Stock updated Successfully");
                                
                                // TODO: Insert order needs to be fixed
                                // Think I need to add the item ID to the insert order statement
                                System.out.println("--------------------");
                                System.out.println("This is the order fields:");
                                System.out.println("This is the items_id: " + itemID);
                                itemnameField.getText();
                                System.out.println("Item Name: " + itemName);
                                quantityField.getText();
                                System.out.println("Requested Quantity: " + requestedQuantity);
                                orderDateField.getText();
                                System.out.println("Order Date: " + orderDate);
                                customerNameField.getText();
                                System.out.println("Customer Name: " + customerName);
                                customerEmailField.getText();
                                System.out.println("Customer Email: " + customerEmail);
                                orderNotesField.getText();
                                System.out.println("Order Notes: " + orderNotes);
                                System.out.println("--------------------");
                                
                                // insert the order into the orders table
                                insertOrderStatement.setInt(1, itemID);
                                    System.out.println(itemID+ " Item ID: " + itemID + " has been added to the orders" +
                                            " " +
                                            "table.");
                                insertOrderStatement.setInt(2, requestedQuantity == 0 ? 1 : requestedQuantity);
                                    System.out.println(requestedQuantity + " Requested Quantity: " + requestedQuantity +
                                            " has been added to the orders table.");
                                insertOrderStatement.setString(3, orderDate);
                                    System.out.println(orderDate + " Order Date: " + orderDate + " has been added to " +
                                            "the orders table.");
                                insertOrderStatement.setString(4, customerName);
                                    System.out.println(customerName + " Customer Name: " + customerName + " has been " +
                                            "added to the orders table.");
                                insertOrderStatement.setString(5, customerEmail);
                                    System.out.println(customerEmail + " Customer Email: " + customerEmail + " has " +
                                            "been added to the orders table.");
                                insertOrderStatement.setString(6, orderNotes);
                                    System.out.println(orderNotes + " Order Notes: " + orderNotes + " has been added " +
                                            "to the orders table.");
                                insertOrderStatement.executeUpdate();
                                InventoryLogger.logInfo("Order added Successfully");
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        
        add(orderPanel);
        setVisible(true);
    }
}
