import javax.swing.*;
import java.awt.*;

public class App {
    private final JFrame frame; // Main application window
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

        initializeComponents(); // Initialize GUI components
        setupLayout(); // Set up the layout of GUI components

        frame.pack(); // Pack the components within the frame
        frame.setVisible(true); // Make the frame visible
    }

    private void initializeComponents() {
        itemNameField = new JTextField(20); // Initialize the item name field
        categoryField = new JTextField(20); // Initialize the item category field
        descriptionField = new JTextField(20); // Initialize the item description field
        unitPriceField = new JTextField(20); // Initialize the unit price field
        quantityInStockField = new JTextField(20); // Initialize the quantity in stock field
        minimumStockLevelField = new JTextField(20); // Initialize the minimum stock level field
        addButton = new JButton("Add Item"); // Initialize the add button
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10)); // Create a panel with a grid layout
        panel.add(new JLabel("Item Name:")); // Add labels and corresponding input fields
        panel.add(itemNameField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Unit Price:"));
        panel.add(unitPriceField);
        panel.add(new JLabel("Quantity in Stock:"));
        panel.add(quantityInStockField);
        panel.add(new JLabel("Minimum Stock Level:"));
        panel.add(minimumStockLevelField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(addButton); // Add the add button

        frame.add(panel); // Add the panel to the frame
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
