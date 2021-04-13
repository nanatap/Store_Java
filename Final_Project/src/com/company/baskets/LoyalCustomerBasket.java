package com.company.baskets;

import com.company.payment.Payable;
import com.company.users.User;


public class LoyalCustomerBasket extends StoreBasket implements Payable {

    public LoyalCustomerBasket(String addressOfTheStore, User user) {
        super(addressOfTheStore, user);
    }

    public double calculateBonus() {
        return Math.floor(this.getTotalAmount() / 5);
    }

    public void printInfo(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "loyalCustomerBasket{" +
                super.toString()  +
                "bonusPoints=" + calculateBonus() +
                '}';
    }

    @Override
    public double pay() {
        double totalPayment = super.pay();

        System.out.println("Total bonus points: " + this.calculateBonus());

        return totalPayment;
    }
}
