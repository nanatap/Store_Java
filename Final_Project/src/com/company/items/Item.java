package com.company.items;

import com.company.InputValidator;

public class Item {

    private static int IdCounter;
    private int id;
    private String name;
    private double price;
    private double quantity;

    public Item(String name, double price, double quantity) {
        this.id = ++IdCounter;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Item() {
        this.id = ++IdCounter;
    }

    public static int getIdCounter() {
        return IdCounter;
    }

    public static void setIdCounter(int idCounter) {
        IdCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double calculateTotalPrice() {
        double totalPrice =  this.getQuantity() * this.getPrice();

        return Math.round(totalPrice * 100.0) / 100.0;
    }

    public void insertItemInStock() {

        System.out.println("Insert item name you would like to add to the stock --> ");
        this.setName(InputValidator.inputString());
        System.out.println("Price of the item -->");
        this.setPrice(InputValidator.inputDouble());
        System.out.println("Quantity in stock for the item -->");
        this.setQuantity(InputValidator.inputDouble());
    }

    @Override
    public String toString() {
        return "Item {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", totalPrice=" + calculateTotalPrice() +
                " }";
    }
}



