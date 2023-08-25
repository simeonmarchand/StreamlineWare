package com.simeonmarchand.streamlineware.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSearchTab extends JPanel{
    private JTextField itemNameField; // Text field for item name
    private JTextField categoryField; // Text field for item category
    private JTextField descriptionField; // Text field for item description
    private JTextField unitPriceField; // Text field for item unit price
    private JTextField quantityInStockField; // Text field for item quantity in stock
    private JTextField minimumStockLevelField; // Text field for item minimum stock level
    private JButton addButton; // Button to add an item
    private JButton searchButton; // Button to search for an item

    public AddSearchTab(){

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchWindow();
            }
        });

    }

    private void openSearchWindow() {
        JFrame searchFrame = new JFrame("Search Window");
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setSize(500, 500);
        
        searchFrame.setVisible(true);
    }
}
