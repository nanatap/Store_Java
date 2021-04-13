package com.company.users.Customer;

public class LoyalCustomer extends SimpleCustomer {

    private static final double BONUS_POINT_RATE = 10;

    public LoyalCustomer(String name, String surname, String username, String password, String address, int phoneNumber) {
        super(name, surname, username, password, address, phoneNumber);
    }

    public static double getBonusPointRate() {
        return BONUS_POINT_RATE;
    }

    public double calculateBonus() {
        double bonusFromPoints = Math.floor(this.getBonusPoints() / 100.0);
        return bonusFromPoints * getBonusPointRate();
    }

    @Override
    public String toString() {
        return "LoyalCustomer {" +
                super.toString()  +
                '}';
    }
}
