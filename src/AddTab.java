import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTab extends JPanel {
    private JTextField itemNameField;
    private JTextField categoryField;
    private JButton addButton;

    public AddTab() {
        setLayout(new GridLayout(3, 2));

        itemNameField = new JTextField();
        categoryField = new JTextField();
        addButton = new JButton("Add Item");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the add item button click
                // Implement your logic here
            }
        });

        add(new JLabel("Item Name:"));
        add(itemNameField);
        add(new JLabel("Category:"));
        add(categoryField);
        add(new JLabel()); // Empty label for spacing
        add(addButton);
    }

    // Getter methods for components
    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getCategoryField() {
        return categoryField;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
