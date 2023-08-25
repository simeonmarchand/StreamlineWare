package com.simeonmarchand.streamlineware;

import com.simeonmarchand.streamlineware.data.Item;
import com.simeonmarchand.streamlineware.data.ItemDAO;
import com.simeonmarchand.streamlineware.ui.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private final App app;
    private final ItemDAO itemDAO;
    private Connection connection;
    public Main() {
        // Initialize the GUI and com.simeonmarchand.streamlineware.data.ItemDAO
        app = new App(); // Changed from StreamlineWareApp
        itemDAO = new ItemDAO();

        // Initialize the database connection
        String dbUrl = "jdbc:mysql://localhost:3306/streamlineware";
        String username = "password";
        String password = "password";
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connected to database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database.");
        }

        setupListeners();
    }

    private void setupListeners() {
        // attach action listeners to "Add" button
        JButton addButton = app.getAddButton();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // retrieve data from input fields
                String name = app.getItemNameField().getText();
                String category = app.getCategoryField().getText();
                String description = app.getDescriptionField().getText();
                BigDecimal unitPrice = new BigDecimal(app.getUnitPriceField().getText());
                int quantityInStock = Integer.parseInt(app.getQuantityInStockField().getText());
                int minimumStockLevel = Integer.parseInt(app.getMinimumStockLevelField().getText());

                // create item object with the retrieved data
                Item item = new Item(name, category, description, unitPrice, quantityInStock, minimumStockLevel);

                // call the DAO method to add the item to the database
                if (itemDAO.addItem(connection, item)) {
                    // show success message and clear input fields
                    JOptionPane.showMessageDialog(null, "Product item added successfully!");
                    app.clearInputFields();
                } else {
                    // show error message if item failed to be added
                    JOptionPane.showMessageDialog(null, "Failed to add item.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
