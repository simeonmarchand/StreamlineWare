package com.simeonmarchand.streamlineware.ui;

import com.simeonmarchand.streamlineware.data.DatabaseConnection;
import com.simeonmarchand.streamlineware.data.Item;
import com.simeonmarchand.streamlineware.data.OrderDAO;
import com.simeonmarchand.streamlineware.logger.InventoryLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Logger;

public class App {
    private final JFrame frame; // Main application window
    private AddSearchTab addSearchTab; // Add/Search tab
    private JTabbedPane tabbedPane; // Tabbed pane to hold the tabs
    private JTextField itemNameField; // Text field for item name
    private JTextField categoryField; // Text field for item category
    private JTextField descriptionField; // Text field for item description
    private JTextField unitPriceField; // Text field for item unit price
    private JTextField quantityInStockField; // Text field for item quantity in stock
    private JTextField minimumStockLevelField; // Text field for item minimum stock level
    private JButton addButton; // Button to add an item
    private JButton searchButton; // Button to search for an item

    public App() {
        frame = new JFrame("StreamlineWare"); // Create the main application window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation

        initializeComponents(createAddTabContent(), createOrdersTabContent()); // Initialize the GUI components
        setupLayout(); // Set up the layout of GUI components

        frame.pack(); // Pack the components within the frame
        frame.setVisible(true); // Make the frame visible
    }

    private void initializeComponents(JPanel createAddTabContent, JPanel createOrdersTabContent) {
        tabbedPane = new JTabbedPane(); // Initialize the tabbed pane
        addSearchTab = new AddSearchTab(); // Initialize the add/search tab
        tabbedPane.addTab("Add/Search", createAddTabContent); // Add the add item tab
        tabbedPane.addTab("Orders Items", createOrdersTabContent); // Add the view items tab
    }

    private void setupLayout() {
        frame.add(tabbedPane); // Add the tabbed pane to the frame
    }


    /*

    Add tab content

     */
    private JPanel createAddTabContent(){
        JPanel addTabPanel = new JPanel(new GridLayout(10, 2, 11, 11)); // Create a panel with 7 rows, 2 columns, and 10px horizontal and vertical gaps

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
        addTabPanel.add(new JLabel());
        addTabPanel.add(addButton);

        searchButton = new JButton("Search Item");
        addTabPanel.add(new JLabel(""));
        addTabPanel.add(searchButton);

        JButton deleteItem = new JButton("Delete Item");
        addTabPanel.add(new JLabel(""));
        addTabPanel.add(deleteItem);

        /*

        Delete button listener

         */
        deleteItem.addActionListener(e -> {
            String query = "DELETE FROM items";
            try(Connection connection = DatabaseConnection.getConnection()){
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "All items deleted");
                    InventoryLogger.logInfo("All items deleted");
                }
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        });

        /*

        Search button listener

         */
        searchButton.addActionListener(e -> {
            AddSearchTab addSearchTab = new AddSearchTab();
            addSearchTab.openSearchWindow();
        });

        JButton searchButton = new JButton("Search Item");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return addTabPanel;
    }

    /*

    Orders tab content

     */

    private JPanel createOrdersTabContent(){
        JPanel ordersTabPanel = new JPanel(new BorderLayout());

        JPanel orderFormPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        orderFormPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        orderFormPanel.add(new JLabel("Item Name:"));
        JTextField itemNameField = new JTextField(20);
        orderFormPanel.add(itemNameField);
        System.out.println(itemNameField.getText());

        orderFormPanel.add(new JLabel("Quantity:"));
        JTextField quantityField = new JTextField(20);
        orderFormPanel.add(quantityField);
        System.out.println(quantityField.getText());

        orderFormPanel.add(new JLabel("Customer Name:"));
        JTextField customerNameField = new JTextField(20);
        orderFormPanel.add(customerNameField);
        System.out.println(customerNameField.getText());

        orderFormPanel.add(new JLabel("Customer Email:"));
        JTextField customerEmailField = new JTextField(20);
        orderFormPanel.add(customerEmailField);
        System.out.println(customerEmailField.getText());

        JButton orderButton = new JButton("Order");
        orderFormPanel.add(new JLabel());
        orderFormPanel.add(orderButton);

        orderButton.addActionListener(e -> {
            //TODO: Implement order button
            // currently just gets the items that are entered into the fields
            // but does not do anything with them yet, need to get it to remove the item from the the DB
            System.out.println("Order button clicked");
            String itemName = itemNameField.getText();
            System.out.println("Your Item name was: " + itemName);
            int quantityOrdered = Integer.parseInt(quantityField.getText());
            System.out.println("Your quantity was: " + quantityOrdered);
            String customerName = customerNameField.getText();
            System.out.println("Your customer name was: " + customerName);
            String customerEmail = customerEmailField.getText();
            System.out.println("Your customer email was: " + customerEmail);

            // create an Item object with retrieved item details
            Item item = new Item(itemName, quantityOrdered, customerName, customerEmail);
            System.out.println("Item object created: " + item);
            InventoryLogger.logInfo("Order placed for " + quantityOrdered + " of " + itemName + " by " + customerName + " (" + customerEmail + ")");

            // update database with new item quantity
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.updateItemQuantity(item);

            // clear input fields
            itemNameField.setText("");
            quantityField.setText("");
            customerNameField.setText("");
            customerEmailField.setText("");
        });

        ordersTabPanel.add(orderFormPanel, BorderLayout.CENTER);

        return ordersTabPanel;
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

    public static void  main(String[] args) {
        SwingUtilities.invokeLater(App::new); // Create the application
    }

    class AddSearchTab extends JPanel{
        private JButton searchButton;

        public AddSearchTab(){
            setupUI();
        }

        private void setupUI(){
            setLayout(new FlowLayout());

            searchButton = new JButton("Search");
            add(searchButton);

            searchButton.addActionListener(e -> openSearchWindow());
        }

        /*

        Opens the search window

         */

        public void openSearchWindow() {
            JFrame searchFrame = new JFrame("Search Window");
            searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            searchFrame.setSize(500, 500);

            // Create and set up the UI components
            JPanel searchPanel = new JPanel(new FlowLayout());

            JLabel nameLabel = new JLabel("Search by product Name: ");
            JTextField nameField = new JTextField(20);
            JButton searchButton = new JButton("Search");
            JButton searchAllButton = new JButton("Search All");

            searchButton.addActionListener(e -> {
                String searchName = nameField.getText();
                performSearchByName(searchName);
            });

            searchAllButton.addActionListener(e -> {
                String searchAll = nameField.getText();
                performAllSearch(searchAll);
            });

            searchPanel.add(nameLabel);
            searchPanel.add(nameField);
            searchPanel.add(searchButton);
            searchPanel.add(searchAllButton);


            searchFrame.add(searchPanel);
            searchFrame.setVisible(true);
        }


        /*


        Searches for all items in the database and displays the item names in a message dialog


         */
        private void performAllSearch(String searchAll) {
            //TODO: Implement search all
            try(Connection connection = DatabaseConnection.getConnection()){

                String query = "SELECT * FROM items";
                // select * from items where field1 like '%" + searchterm + "%' and field2 like
                System.out.println(query);

                try(PreparedStatement statement = connection.prepareStatement(query)){
                    ResultSet resultSet = statement.executeQuery();

                    StringBuilder resultMessage = new StringBuilder("Search results: \n");

                    boolean foundResults = false;

                    while(resultSet.next()){
                        String itemName = resultSet.getString("name");
                        String itemCategory = resultSet.getString("category");
                        String itemDescription = resultSet.getString("description");
                        BigDecimal itemUnitPrice = resultSet.getBigDecimal("unit_price");
                        int itemQuantityInStock = resultSet.getInt("quantity_in_stock");
                        int itemMinimumStockLevel = resultSet.getInt("minimum_stock_level");
                        resultMessage.append(itemName).append("\n");
                        resultMessage.append(itemCategory).append("\n");
                        resultMessage.append(itemDescription).append("\n");
                        resultMessage.append(itemUnitPrice).append("\n");
                        resultMessage.append(itemQuantityInStock).append("\n");
                        resultMessage.append(itemMinimumStockLevel).append("\n");
                        foundResults = true;
                    }

                    if (foundResults){
                        JOptionPane.showMessageDialog(this, resultMessage.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                        InventoryLogger.logInfo("Search results: " + resultMessage.toString());
                    } else {
                        JOptionPane.showMessageDialog(this, "No results found for '" + searchAll + "'.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                        InventoryLogger.logInfo("No results found for '" + searchAll + "'.");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /*

        Search by name method

         */

        private void performSearchByName(String searchName) {
            //TODO: Implement search by name
            if (searchName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a name to search for");
                return;
            }

            // connect to the DB and perform the search
            try (Connection connection = DatabaseConnection.getConnection()) {

                String query = "SELECT * FROM items WHERE name LIKE ?";

                try(PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, "%" + searchName + "%");

                    ResultSet resultSet = statement.executeQuery();

                    StringBuilder resultMessage = new StringBuilder("Search results: \n");

                    boolean foundResults = false;

                    while(resultSet.next()){
                        String itemName = resultSet.getString("name");
                        resultMessage.append(itemName).append("\n");
                        foundResults = true;
                    }

                    if (foundResults){
                        JOptionPane.showMessageDialog(this, resultMessage.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                        InventoryLogger.logInfo("Search results: " + resultMessage.toString());
                    } else {
                        JOptionPane.showMessageDialog(this, "No results found for '" + searchName + "'.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                        InventoryLogger.logInfo("No results found for '" + searchName + "'.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while searching for '" + searchName + "'.", "Search Results", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    /*


    GETTER METHODS TO ACCESS GUI COMPONENTS


     */

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

    public JButton getSearchButton() {
        return searchButton;
    }

}
