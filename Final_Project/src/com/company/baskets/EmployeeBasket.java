package com.company.baskets;

import com.company.payment.Payable;
import com.company.users.User;


public class EmployeeBasket extends StoreBasket implements Payable {

    public EmployeeBasket(String addressOfTheStore, User user) {
        super(addressOfTheStore, user);
    }

    public double calculateBonus() {
        return Math.floor(this.getTotalAmount() / 15);
    }

    public void printInfo(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "EmployeeBasket{" +
                super.toString()  +
                "bonusPointsForEmployees=" + calculateBonus() +
                '}';
    }

    @Override
    public double pay() {
        double totalPayment = super.pay();

        System.out.println("Total Bonus Points: " + this.calculateBonus());

        return totalPayment;
    }
}
