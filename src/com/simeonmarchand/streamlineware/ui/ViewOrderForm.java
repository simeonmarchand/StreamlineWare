package com.simeonmarchand.streamlineware.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ViewOrderForm extends JFrame{
    
    public ViewOrderForm(){
        setTitle("View Order");
        setSize(500, 500);
        JPanel orderPanel = new JPanel(new GridLayout(8,2, 10, 10));
        JButton viewAllOrdersButton = new JButton("View All Orders");
        JButton viewOrderButton = new JButton("View Order");
        
        orderPanel.add(new JLabel());
    }
}
