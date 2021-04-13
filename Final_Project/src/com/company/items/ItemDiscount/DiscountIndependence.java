package com.company.items.ItemDiscount;

import com.company.InputValidator;

public class DiscountIndependence extends ItemDiscount {

    public double discountIndependence;

    public DiscountIndependence() {

    }

    public DiscountIndependence(String name, double price, double quantity, double discount, double discountIndependence) {
        super(name, price, quantity, discount);
        this.discountIndependence = discountIndependence;
    }


    public double getDiscountIndependence() {
        return discountIndependence;
    }

    public void setDiscountIndependence(double discountIndependence) {
        this.discountIndependence = discountIndependence;
    }

    public double calculateDiscount() {

        double totalDiscount = this.getDiscount() + this.getDiscountIndependence();

        double discount = this.getPrice() * totalDiscount * this.getQuantity();

        return Math.round(discount * 100.0) / 100.0;
    }


    public void printInfo () {
        System.out.println(this.toString());
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

        this.setDiscountIndependence(discountAmount);
    }


    @Override
    public String toString() {
        return "DiscountIndependence{" +
                super.toString() +
                ", discountIndependence=" + getDiscountIndependence() +
                '}';
    }
}
