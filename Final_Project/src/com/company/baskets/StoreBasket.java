package com.company.baskets;

import com.company.payment.Payable;
import com.company.items.Item;
import com.company.users.User;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StoreBasket  implements Payable {

    private static int IdCounter;
    private int id;
    private static final double VAT = 1.25;
    private ArrayList<Item> listOfItems = new ArrayList<>();
    private String addressOfTheStore;
    private LocalDateTime purchaseTime;
    private boolean isDiscounted = false;
    private User user;

    public StoreBasket(String addressOfTheStore, User user) {
        this.id = ++IdCounter;
        this.addressOfTheStore = addressOfTheStore;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdCounter() {
        return IdCounter;
    }

    public double getNetAmount() {

        return listOfItems.stream().mapToDouble(Item::calculateTotalPrice).sum();
    }


    public double getTotalAmount() {

        double getAmount = getNetAmount() * this.getVat();

        if(isDiscounted){
            getAmount *= 0.9; // 10 percent discount
        }

        getAmount = Math.round(getAmount * 100.0) / 100.0;
        return getAmount;
    }


    public double getVat() {
        return VAT;
    }


    public void applyChristmasDiscount(){

        isDiscounted = true;
    }

    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }


    public void addItemToList(Item item){

        listOfItems.add(item);
    }

    public void removeItem(Item item) {

        listOfItems.remove(item);
    }

    public void removeAllItems(){

        listOfItems = new ArrayList<>();
    }

    public String getAddressOfTheStore() {
        return addressOfTheStore;
    }

    public void setAddressOfTheStore(String addressOfTheStore) {
        this.addressOfTheStore = addressOfTheStore;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public void printInfo(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "StoreBasket{" +
                "id=" + id +
                ", NetAmount=" + getNetAmount() +
                ", TotalAmount=" + getTotalAmount() +
                ", vat=" + getVat() +
                ", listOfItems=" + listOfItems +
                ", addressOfTheStore='" + addressOfTheStore + '\'' +
                ", purchaseTime=" + purchaseTime +
                ", isDiscounted=" + isDiscounted +
                ", userName=" + user.getName() + " " + user.getSurname() +
                '}';
    }

    @Override
    public double pay() {
        System.out.println("Total Payment: " + this.getTotalAmount());

        setPurchaseTime(LocalDateTime.now());

        return this.getTotalAmount();
    }
}
