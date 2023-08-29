package com.simeonmarchand.streamlineware.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaceOrderForm extends JFrame {
    private JTextField itemnameField;
    private JTextField quantityField;
    private JTextField orderDateField;
    private JTextField customerNameField;
    private JTextField customerEmailField;
    private JTextField orderNotesField;
    
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
                dispose();
            }
        });
        
        add(orderPanel);
        setVisible(true);
    }
}
