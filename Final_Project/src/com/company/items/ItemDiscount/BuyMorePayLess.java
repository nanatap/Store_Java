package com.company.items.ItemDiscount;

import com.company.InputValidator;
import com.company.items.Item;

public class BuyMorePayLess extends Item {

    private static final int MAX_ITEMS = 10;
    private double discountPrice = 0;
    private double discountQuantity = 0;

    public BuyMorePayLess() {

    }

    public BuyMorePayLess(String name, double price, double quantity, double discountPrice, double discountQuantity) {
        super(name, price, quantity);

        if (quantity > MAX_ITEMS) {
            throw new IllegalArgumentException("Cannot buy more than + " + MAX_ITEMS);
        }

        this.discountPrice = discountPrice;
        this.discountQuantity = discountQuantity;
    }

    public static int getMaxItems() {
        return MAX_ITEMS;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(double discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    public void insertItemInStock(){
        super.insertItemInStock();

        System.out.print("Insert the discount quantity of the item --> ");

        double quantity = -1;

        while (quantity < 0 || quantity > BuyMorePayLess.getMaxItems()) {
            quantity = InputValidator.inputDouble();

            if (quantity < 0 || quantity > BuyMorePayLess.getMaxItems()) {
                System.out.println("Please enter discount between 0 / 1. For example, 0.90 for 90 %");
            }
        }

        setDiscountQuantity(quantity);
        System.out.print("Discount price --> ");
        setDiscountPrice(InputValidator.inputDouble());

    }


    public double calculateDiscount() {

        double discountCount = Math.floor(this.getQuantity() / this.getDiscountQuantity());

        double discountedCount = (this.getDiscountQuantity() - this.getDiscountPrice()) * discountCount;

        double discount = (discountedCount) * super.getPrice();

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
        return "BuyMorePayLess {" +
                super.toString()  +
                ", discountPrice=" + discountPrice +
                ", discountQuantity=" + discountQuantity +
                '}';
    }
}

