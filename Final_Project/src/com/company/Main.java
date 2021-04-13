package com.company;

import com.company.items.ItemDiscount.BuyMorePayLess;
import com.company.items.Item;
import com.company.items.ItemDiscount.DiscountIndependence;
import com.company.items.ItemDiscount.ItemDiscount;
import com.company.items.ItemDiscount.ItemTakeItAll;
import com.company.users.Cashier;
import com.company.users.Customer.Employee;
import com.company.users.Customer.LoyalCustomer;
import com.company.users.Customer.SimpleCustomer;
import com.company.users.Manager;
import com.company.users.User;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Manager manager = new Manager("Merry", "Onestom", "merry19", "fmlKb_1",
                "Stor Gata", 45678909, 0045435670, 223);
        Cashier cashier = new Cashier("Meri", "Li", "merili", "jkb80lp",
                "Luise 40 Tallinn", 45678976, 12);
        Employee employee= new Employee("nina", "Jones", "nina87", "vlsdf4",
                "lala",4353245);
        LoyalCustomer loyal = new LoyalCustomer("Anita", "kardigan", "anita_11",
                "dfglds5", "lcdfjg",
                45678956);
        SimpleCustomer simple = new SimpleCustomer("dlgk", "sdfg", "sdfg",
                "sdfgh", "sdfg", 435673456);

        ArrayList<User> users = new ArrayList<>();

        users.add(manager);
        users.add(cashier);
        users.add(employee);
        users.add(loyal);
        users.add(simple);

        ItemDiscount itemDiscount = new ItemDiscount("Kafka on the shore", 1, 10, 2);
        DiscountIndependence discountIndependence = new DiscountIndependence("Animal farm", 2, 11, 0.05, 0.03);
        ItemTakeItAll itemTakeItAll = new ItemTakeItAll("Norwegian woods", 3, 17, 0.05, 3);
        BuyMorePayLess buyMorePayLess = new BuyMorePayLess("Twelve chairs", 4, BuyMorePayLess.getMaxItems(), 3, 2);

        Item.setIdCounter(4);

        ArrayList<Item> stocks = new ArrayList<>();

        stocks.add(itemDiscount);
        stocks.add(discountIndependence);
        stocks.add(itemTakeItAll);
        stocks.add(buyMorePayLess);

        Store store = new Store(users, stocks);

        store.askCredentials();
        store.chooseLanguage();
        store.displayMenu();

    }

}
