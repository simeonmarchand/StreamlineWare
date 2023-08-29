package com.simeonmarchand.streamlineware.data;

public class Order {
    private int orderId;
    private int itemId;
    private int quantityOrdered;
    private String orderDate;
    private String fullName;
    private String emailAddress;

    public Order(int orderID, int itemID, int quantityOrdered, String orderDate, String fullName, String emailAddress) {
        this.orderId = orderID;
        this.itemId = itemID;
        this.quantityOrdered = quantityOrdered;
        this.orderDate = orderDate;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
