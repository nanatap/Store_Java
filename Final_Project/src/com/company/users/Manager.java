package com.company.users;

public class Manager extends User {

    private int InternalPhoneNumber;
    private int pin;

    public Manager(String name, String surname, String username, String password, String address, int phoneNumber, int internalPhoneNumber, int pin) {
        super(name, surname, username, password, address, phoneNumber);
        InternalPhoneNumber = internalPhoneNumber;
        this.pin = pin;
    }

    public int getInternalPhoneNumber() {
        return InternalPhoneNumber;
    }

    public void setInternalPhoneNumber(int internalPhoneNumber) {
        InternalPhoneNumber = internalPhoneNumber;
    }

    public int getPin() {
        return pin;
    }

    public void printInfo(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Manager{" +
                super.toString()  +
                "InternalPhoneNumber=" + InternalPhoneNumber +
                ", pin=" + pin +
                '}';
    }

}
