package com.simeonmarchand.streamlineware.data;

import java.math.BigDecimal;

public class Item {
    private String name;
    private String category;
    private String description;
    private BigDecimal unitPrice;
    private int quantityInStock;
    private int minimumStockLevel;

    public Item(String name, String category, String description, BigDecimal unitPrice, int quantityInStock, int minimumStockLevel) {
        // initialize the fields
        this.name = name;
        this.category = category;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantityInStock = quantityInStock;
        this.minimumStockLevel = minimumStockLevel;
    }

    // getter methods for retrieving the values of the item's fields
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }
}