import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class App {
    private JFrame frame;
    private JTextField itemNameField;
    private JTextField categoryField;
    private JTextField descriptionField;
    private JTextField unitPriceField;
    private JTextField quantityInStockField;
    private JTextField minimumStockLevelField;
    private JButton addButton;

    public App() {
        frame = new JFrame("StreamlineWare");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeComponents();
        setupLayout();

        frame.pack();
        frame.setVisible(true);
    }

    private void initializeComponents() {
        itemNameField = new JTextField(20);
        categoryField = new JTextField(20);
        descriptionField = new JTextField(20);
        unitPriceField = new JTextField(20);
        quantityInStockField = new JTextField(20);
        minimumStockLevelField = new JTextField(20);
        addButton = new JButton("Add Item");
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.add(new JLabel("Item Name:"));
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
        panel.add(addButton);

        frame.add(panel);
    }

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

    public void clearInputFields() {
        itemNameField.setText("");
        categoryField.setText("");
        descriptionField.setText("");
        unitPriceField.setText("");
        quantityInStockField.setText("");
        minimumStockLevelField.setText("");
    }
}
