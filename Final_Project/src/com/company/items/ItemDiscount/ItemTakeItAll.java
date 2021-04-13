package com.company.items.ItemDiscount;

public class ItemTakeItAll extends ItemDiscount {

    private int minimumItemCount;

    public ItemTakeItAll() {
    }

    public ItemTakeItAll(String name, double price, double quantity, double discount, int minimumItemCount) {
        super(name, price, quantity, discount);
        this.minimumItemCount = minimumItemCount;
    }

    public int getMinimumItemCount() {
        return minimumItemCount;
    }

    public void setMinimumItemCount(int minimumItemCount) {
        this.minimumItemCount = minimumItemCount;
    }

    public double calculateDiscount() {


        if (getQuantity() > getMinimumItemCount()) {
            return super.calculateDiscount();
        } else {
            return 0;
        }

    };

    public void printInfo () {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "ItemTakeItAll {" +
                super.toString() +
                ", minimumItemCount=" + minimumItemCount +
                '}';
    }
}
