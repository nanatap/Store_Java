package com.company.items.ItemDiscount;

import com.company.InputValidator;
import com.company.items.Item;

public class ItemDiscount extends Item {

    private double discount;

    public ItemDiscount() {

    }

    public ItemDiscount(String name, double price, double quantity, double discount) {
        super(name, price, quantity);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void insertItemInStock(){
        super.insertItemInStock();

        System.out.println("Discount amount (Between 0 / 1)-->");
        double discountAmount = -1;

        while (discountAmount < 0 || discountAmount > 1) {
            discountAmount = InputValidator.inputDouble();

            if (discountAmount < 0 || discountAmount > 1) {
                System.out.println("Please enter discount between 0 / 1. For example, 0.90 for 90 %");
            }
        }

        this.setDiscount(discountAmount);
    }

    public double calculateDiscount() {

        double discount = this.getPrice() * this.getDiscount() * this.getQuantity();

        return Math.round(discount * 100.0) / 100.0;
    }

    @Override
    public double calculateTotalPrice() {
        return super.calculateTotalPrice() - (calculateDiscount());
    }

    public void printInfo () {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "ItemDiscount {" +
                super.toString()  +
                ", discount=" + getDiscount() +
                ", calculatedDiscount=" + calculateDiscount() +
                '}';
    }
}
