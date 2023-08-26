package com.simeonmarchand.streamlineware.ui;

import com.simeonmarchand.streamlineware.data.DatabaseConnection;
import com.simeonmarchand.streamlineware.data.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class App {
    private final JFrame frame; // com.simeonmarchand.streamlineware.Main application window
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

    private JPanel createAddTabContent(){
        JPanel addTabPanel = new JPanel(new GridLayout(8, 2, 10, 10)); // Create a panel with 7 rows, 2 columns, and 10px horizontal and vertical gaps

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


        /*
        Add button listener
         */

        addButton.addActionListener(e -> {
            String name = itemNameField.getText();
            String category = categoryField.getText();
            String description = descriptionField.getText();
            BigDecimal unitPrice = new BigDecimal(unitPriceField.getText());
            int quantityInStock = Integer.parseInt(quantityInStockField.getText());
            int minimumStockLevel = Integer.parseInt(minimumStockLevelField.getText());

            Item item = new Item(name, category, description, unitPrice, quantityInStock, minimumStockLevel);

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

    private JPanel createOrdersTabContent(){
        JPanel ordersTabPanel = new JPanel();
        // add UI components specific to the orders tab
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

        private void performAllSearch(String searchAll) {
            //TODO: Implement search all
            try(Connection connection = DatabaseConnection.getConnection()){

                String query = "SELECT * FROM items";
                System.out.println(query);

                try(PreparedStatement statement = connection.prepareStatement(query)){
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
                    } else {
                        JOptionPane.showMessageDialog(this, "No results found for '" + searchAll + "'.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

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
                    } else {
                        JOptionPane.showMessageDialog(this, "No results found for '" + searchName + "'.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
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

}
