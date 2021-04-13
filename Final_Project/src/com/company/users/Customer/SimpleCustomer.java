package com.company.users.Customer;

import com.company.users.User;

public class SimpleCustomer extends User {

    private double bonusPoints;

    public SimpleCustomer(String name, String surname, String username, String password, String address, int phoneNumber) {
        super(name, surname, username, password, address, phoneNumber);
    }

    public double getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(double bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public void addBonusPoints(double bonusPoints) {
        this.bonusPoints+=bonusPoints;
    }

    public double calculateBonus(){
        return 0;
    }

    @Override
    public String toString() {
        return "SimpleCustomer{" +
                super.toString()  +
                ", calculatedBonus=" + this.calculateBonus() +
                "}";
    }
}
