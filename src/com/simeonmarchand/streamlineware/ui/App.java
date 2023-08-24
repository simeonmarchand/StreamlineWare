package com.simeonmarchand.streamlineware.ui;

import com.simeonmarchand.streamlineware.data.Item;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class App {
    private final JFrame frame; // com.simeonmarchand.streamlineware.Main application window
    private JTabbedPane tabbedPane; // Tabbed pane to hold the tabs
    private JTextField itemNameField; // Text field for item name
    private JTextField categoryField; // Text field for item category
    private JTextField descriptionField; // Text field for item description
    private JTextField unitPriceField; // Text field for item unit price
    private JTextField quantityInStockField; // Text field for item quantity in stock
    private JTextField minimumStockLevelField; // Text field for item minimum stock level
    private JButton addButton; // Button to add an item

    public App() {
        frame = new JFrame("StreamlineWare"); // Create the main application window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation

        initializeComponents(createAddTabContent(), createSearchTabContent(), createOrdersTabContent()); // Initialize the GUI components
        setupLayout(); // Set up the layout of GUI components

        frame.pack(); // Pack the components within the frame
        frame.setVisible(true); // Make the frame visible
    }

    private void initializeComponents(JPanel createAddTabContent, JPanel createSearchTabContent, JPanel createOrdersTabContent) {
        tabbedPane = new JTabbedPane(); // Initialize the tabbed pane
        tabbedPane.addTab("Add Item", createAddTabContent); // Add the add item tab
        tabbedPane.addTab("Search Items", createSearchTabContent); // Add the search items tab
        tabbedPane.addTab("Orders Items", createOrdersTabContent); // Add the view items tab
    }

    private void setupLayout() {
        frame.add(tabbedPane); // Add the tabbed pane to the frame
    }

    private JPanel createAddTabContent(){
        JPanel addTabPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // Create a panel with 7 rows, 2 columns, and 10px horizontal and vertical gaps
        // add com.simeonmarchand.streamlineware.ui components specific to the add tab
        addTabPanel.add(new JLabel("Item Name:"));
        addTabPanel.add(itemNameField = new JTextField(20));

        addTabPanel.add(new JLabel("Category:"));
        addTabPanel.add(categoryField = new JTextField(20));

        addTabPanel.add(new JLabel("Description:"));
        addTabPanel.add(descriptionField = new JTextField(20));

        addTabPanel.add(new JLabel("Unit Price:"));
        addTabPanel.add(unitPriceField = new JTextField(20));

        addTabPanel.add(new JLabel("Quantity in Stock:"));
        addTabPanel.add(quantityInStockField = new JTextField(20));

        addTabPanel.add(new JLabel("Minimum Stock Level:"));
        addTabPanel.add(minimumStockLevelField = new JTextField(20));

        addButton = new JButton("Add Item");
        addTabPanel.add(new JLabel()); // Add an empty label to fill the space
        addTabPanel.add(addButton);

        // set up action listeners for the add button
        addButton.addActionListener( e -> {
           String name = itemNameField.getText();
           String category = categoryField.getText();
           String description = descriptionField.getText();
           BigDecimal unitPrice = new BigDecimal(unitPriceField.getText());
           int quantityInStock = Integer.parseInt(quantityInStockField.getText());
           int minimumStockLevel = Integer.parseInt(minimumStockLevelField.getText());

           Item item = new Item(name, category, description, unitPrice, quantityInStock, minimumStockLevel);
        });

        return addTabPanel;
    }

    private JPanel createSearchTabContent(){
        JPanel searchTabPanel = new JPanel();
        // add com.simeonmarchand.streamlineware.ui components specific to the search tab
        return searchTabPanel;
    }

    private JPanel createOrdersTabContent(){
        JPanel ordersTabPanel = new JPanel();
        // add com.simeonmarchand.streamlineware.ui components specific to the orders tab
        return ordersTabPanel;
    }

    // Getter methods to access GUI components
    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getCategoryField() {
        return categoryField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public JTextField getUnitPriceField() {
        return unitPriceField;
    }

    public JTextField getQuantityInStockField() {
        return quantityInStockField;
    }

    public JTextField getMinimumStockLevelField() {
        return minimumStockLevelField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    // Method to clear input fields
    public void clearInputFields() {
        itemNameField.setText("");
        categoryField.setText("");
        descriptionField.setText("");
        unitPriceField.setText("");
        quantityInStockField.setText("");
        minimumStockLevelField.setText("");
    }
}
