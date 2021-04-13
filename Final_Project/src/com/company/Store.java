package com.company;

import com.company.baskets.EmployeeBasket;
import com.company.baskets.LoyalCustomerBasket;
import com.company.baskets.StoreBasket;
import com.company.items.*;
import com.company.items.ItemDiscount.BuyMorePayLess;
import com.company.items.ItemDiscount.DiscountIndependence;
import com.company.items.ItemDiscount.ItemDiscount;
import com.company.items.ItemDiscount.ItemTakeItAll;
import com.company.payment.PaymentProvider;
import com.company.users.Cashier;
import com.company.users.Customer.Employee;
import com.company.users.Customer.LoyalCustomer;
import com.company.users.Manager;
import com.company.users.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


public class Store {

    ArrayList<StoreBasket> basketServed = new ArrayList<>();
    ArrayList<StoreBasket> basketsPaid = new ArrayList<>();

    Locale svLocale;
    Locale currentLocale;
    ResourceBundle enBundle;
    ResourceBundle svBundle;
    ResourceBundle currentBundle;

    String menu = null;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Item> stocks = new ArrayList<>();
    private StoreBasket currentBasket = null;
    private User currentUser;


    public Store(ArrayList<User> users, ArrayList<Item> stocks) {
        this.users = users;
        this.stocks = stocks;

        svLocale = new Locale("sv","SE");

        enBundle = ResourceBundle.getBundle("MenuBundle", Locale.US);
        svBundle = ResourceBundle.getBundle("MenuBundle", svLocale);

        currentBundle = enBundle;
    }


    public StoreBasket createNewBasket() {

        System.out.println(currentBundle.getString("customer_type"));

        System.out.println("*****");
        System.out.println("" +
                "1)" + currentBundle.getString("simple_customer") + "\n" +
                "2)" + currentBundle.getString("loyal_customer") + "\n" +
                "3)" + currentBundle.getString("employee") + "\n");

        System.out.print(currentBundle.getString("choose_activity_accordingly"));

        int userChoice = InputValidator.inputInt(3);

        StoreBasket newBasket;

        switch (userChoice) {

            case 1:

                newBasket = new StoreBasket("Luise 40", currentUser);

                break;
            case 2:

                newBasket = new LoyalCustomerBasket("Luise 40", currentUser);

                break;

            case 3:

                newBasket = new EmployeeBasket("Luise 40", currentUser);

                break;

            default:
                throw new IllegalStateException(currentBundle.getString("unexpected_value") + userChoice);
        }

        basketServed.add(newBasket);
        currentBasket = newBasket;
        System.out.println(currentBundle.getString("new_basket_created") + newBasket);

        return newBasket;
    }

    public void chooseLanguage() {

        boolean languageSelected = false;
        while (!languageSelected) {
            System.out.println("*** Menu ****");
            System.out.println("english || swedish" + "\n" + "type in your desired language: ");
            menu = InputValidator.inputString();
            if (menu.equals("english")) {
                currentLocale = Locale.US;
                currentBundle = enBundle;
                languageSelected = true;
            } else if (menu.equals("swedish")) {
                currentLocale = svLocale;
                currentBundle  = svBundle;
                languageSelected = true;
            }
        }
    }
//
    public void userMenu() {


        boolean doNotExit = true;
        while (doNotExit) {
            System.out.println("*****");
            System.out.println("1)" + currentBundle.getString("create_new_basket") + "\n" +
                    "2)" +  currentBundle.getString("add_item_to_basket") + "\n" +
                    "3)" + currentBundle.getString("delete_item_from_basket") + "\n" +
                    "4)" +  currentBundle.getString("print_info_for_basket") + "\n" +
                    "5)" +  currentBundle.getString("apply_christmas_discount") + "\n" +
                    "6)" + currentBundle.getString("go_to_cart_and_pay") + "\n" +
                    "7)" + currentBundle.getString("exit"));
            System.out.print(currentBundle.getString("choose_activity_accordingly"));

            int userChoice = InputValidator.inputInt(7);

            switch (userChoice) {

                case 1:

                    System.out.println(currentBundle.getString("store_basket_created") + this.createNewBasket().getId());

                    break;
                case 2:

                    insertItem();

                    break;

                case 3:

                    removeItem();

                    break;

                case 4:

                    basketInformation();

                    break;

                case 5:

                    applyChristmasPromotion();

                    break;

                case 6:

                    paymentDetails();

                    break;

                case 7:
                    doNotExit = false;
                    System.out.println(currentBundle.getString("goodbye"));
                    break;
            }

        }
    }

    public void ManagersMenu() {

        boolean doNotExit = true;
        while (doNotExit) {

            System.out.println("*****");
            System.out.println("1)" + currentBundle.getString("insert_item_in_stock") + "\n" +
                    "2)" + currentBundle.getString("exit"));
            System.out.print(currentBundle.getString("choose_activity_accordingly"));

            int ManagerChoice = InputValidator.inputInt(2);

            switch (ManagerChoice) {


                case 1:

                    defineDiscount();

                    break;

                case 2:

                    doNotExit = false;
                    System.out.println(currentBundle.getString("goodbye"));
                    break;


            }

        }
    }
//
    public void displayMenu() {

        if(currentUser instanceof Manager){
            this.ManagersMenu();
        }else if (currentUser instanceof Cashier){
            this.employeeMenu();
        }else {
            this.userMenu();
        }
    }
//
//
    public void askCredentials() {
        System.out.println(currentBundle.getString("enter_credentials") + ":");

        while (currentUser == null) {
            System.out.println(currentBundle.getString("username"));
            String login = InputValidator.inputString();
            System.out.println(currentBundle.getString("password"));
            String password = InputValidator.inputString();

            currentUser = credentialValidation(login, password);

            if (currentUser == null) {
                System.out.println(currentBundle.getString("incorrect_credentials"));
                continue;
            } else {
                if(currentUser instanceof Manager){
                    while(true) {
                        System.out.println(currentBundle.getString("insert_pin"));
                        int pin = InputValidator.inputInt(999);
                        if (pin == ((Manager) currentUser).getPin()) {
                            break;

                        } else {
                            System.out.println(currentBundle.getString("incorrect_pin"));
                        }
                    }
                } else if(currentUser instanceof Cashier){
                    ((Cashier) currentUser).setLoginTime(LocalDateTime.now());
                }

                System.out.println(currentBundle.getString("welcome"));
            }
        }
    }
//
    public User credentialValidation(String login, String password) {

//        for (int i = 0; i < users.size(); i++) {
//            if (login.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())) {
//                return users.get(i);
//            }
//        }

        return users.stream()
                .filter(user -> (user.getUsername().equals(login) && user.getPassword().equals(password)))
                .findFirst()
                .orElse(null);
    }
//
    public void insertItem() {

        if (currentBasket == null) {
            currentBasket = createNewBasket();
        }

        System.out.println(currentBundle.getString("insert_item"));
        String itemName = InputValidator.inputString();

//        for (int i = 0; i < stocks.size(); i++) {
//            if (itemName.equals(stocks.get(i).getName())) {
//                currentBasket.addItemToList(stocks.get(i));
//                System.out.println("Item has been added to the basket.");
//                return;
//            }
//        }

        Item item =  stocks.stream()
                .filter(stock -> (stock.getName().equals(itemName)))
                .findFirst()
                .orElse(null);

        if (item != null) {
            System.out.println(currentBundle.getString("enter_quantity") + ": " + item.getQuantity());
            int quantity = InputValidator.inputInt((int) item.getQuantity());
            stocks.get(stocks.indexOf(item)).setQuantity(item.getQuantity() - quantity);
            item.setQuantity(quantity);

            currentBasket.addItemToList(item);
            System.out.println(currentBundle.getString("item_added"));
            return;
        }

        System.out.println(currentBundle.getString("error_outOfStock"));
    }
//
//
    public void removeItem() {
        System.out.println(currentBundle.getString("item_to_be_removed"));
        String removeItem = InputValidator.inputString();

        Item item =  stocks.stream()
                .filter(stock -> (stock.getName().equals(removeItem)))
                .findFirst()
                .orElse(null);

        if (item != null) {
            currentBasket.removeItem(item);
            System.out.println(currentBundle.getString("item_has_been_removed"));
            return;
        }

        System.out.println(currentBundle.getString("cannot_find_item"));

    }
//
//
    public void basketInformation() {

        System.out.println(currentBundle.getString("basket_id"));

        int basketId = InputValidator.inputInt(StoreBasket.getIdCounter());

        StoreBasket basket =  basketServed.stream()
                .filter(basketServed -> (basketServed.getId() == basketId))
                .findFirst()
                .orElse(null);

        if (basket != null) {
            System.out.println(basket);
            return;
        }

        System.out.println(currentBundle.getString("no_such_basket"));

    }

    public void applyChristmasPromotion(){

        currentBasket.applyChristmasDiscount();
        System.out.println(currentBundle.getString("ten_percent_discount") + currentBasket.getTotalAmount());

    }



    public void defineDiscount(){

        System.out.println(currentBundle.getString("discount_type"));
        System.out.println("1)" + currentBundle.getString("discount") +  "\n" +
                "2)" + currentBundle.getString("discount_independence") + "\n" +
                "3)" + currentBundle.getString("buy_more_pay_less") + "\n" +
                "4)" + currentBundle.getString("take_it_all") + "\n");
        int discountChoice = InputValidator.inputInt(4);

        switch (discountChoice) {


            case 1:

                ItemDiscount item = new ItemDiscount();
                item.insertItemInStock();
                stocks.add(item);
                System.out.println(currentBundle.getString("item_added_to_stock"));
                item.printInfo();

                break;

            case 2:

                DiscountIndependence item2 = new DiscountIndependence();
                item2.insertItemInStock();
                stocks.add(item2);
                System.out.println(currentBundle.getString("item_added_discount_applied"));
                item2.printInfo();

                break;

            case 3:

                BuyMorePayLess item3 = new BuyMorePayLess();
                item3.insertItemInStock();
                stocks.add(item3);
                System.out.println(currentBundle.getString("item_added_discount_applied"));
                item3.printInfo();

                break;

            case 4:
                ItemTakeItAll item4 = new ItemTakeItAll();
                item4.insertItemInStock();
                stocks.add(item4);
                System.out.println(currentBundle.getString("item_added_discount_applied"));
                item4.printInfo();
                break;

        }




    }

    public  void employeeMenu() {

        boolean doNotExit = true;
        while (doNotExit) {
            System.out.println("*****");
            System.out.println("" +
                    "1)" + currentBundle.getString("create_new_basket") + "\n" +
                    "2)" + currentBundle.getString("add_item_to_basket") + "\n" +
                    "3)" + currentBundle.getString("delete_item_from_basket")+ "\n" +
                    "4)" + currentBundle.getString("display_basket") + "\n" +
                    "5)" + currentBundle.getString("go_to_cart_and_pay") + "\n" +
                    "6)" + currentBundle.getString("remove_all_items") + "\n" +
                    "7)" + currentBundle.getString("order_history") + "\n" +
                    "8)" + currentBundle.getString("exit") + "\n");
            System.out.print(currentBundle.getString("choose_activity_accordingly"));
            int employeeChoice = InputValidator.inputInt(8);

            switch (employeeChoice) {

                case 1:

                    createNewBasket();

                    break;

                case 2:

                    insertItem();

                    break;

                case 3:

                    removeItem();

                    break;

                case 4:

                    basketInformation();

                    break;

                case 5:

                    paymentDetails();

                    break;

                case 6:

                    currentBasket.removeAllItems();

                    break;

                case 7:

                    System.out.println(basketsPaid);

                    break;

                case 8:

                    doNotExit = false;

                    if(currentUser instanceof Cashier){
                        ((Cashier) currentUser).setLogoutTime(LocalDateTime.now());

                        System.out.println(currentBundle.getString("my_account"));
                        System.out.println(currentBundle.getString("hours_of_work") + ((Cashier) currentUser).getHoursOfWork() + " minutes\n" +
                                currentBundle.getString("current_pay") + ((Cashier) currentUser).pay() + "$\n" + currentBundle.getString("your_bonus") + ((Cashier) currentUser).calculateBonus());
                    }

                    System.out.println(currentBundle.getString("goodbye"));

                    break;

            }

        }

    }

    public void paymentDetails(){

        double totalPrice = currentBasket.getTotalAmount();
        System.out.println(currentBundle.getString("total_amount") + totalPrice);
        System.out.print(currentBundle.getString("credit_card_details"));
        String cardNum = InputValidator.inputString();
        System.out.print(currentBundle.getString("enter_cvv"));
        String cvv = InputValidator.inputString();
        System.out.println(currentBundle.getString("year"));
        int year = InputValidator.inputInt(2025);
        System.out.println(currentBundle.getString("month"));
        int month = InputValidator.inputInt(12);

        PaymentProvider paymentProvider = new PaymentProvider(cardNum,month,year,cvv);

        boolean isValid = paymentProvider.cardValidation();

        if (!isValid) {
            System.out.println(currentBundle.getString("something_went_wrong"));
            return;
        }

        System.out.println(currentBundle.getString("insert_amount"));

        double payment = InputValidator.inputDouble();
        if(payment == totalPrice){
            System.out.println(currentBundle.getString("successful_payment"));

            if (currentUser instanceof Employee && currentBasket instanceof EmployeeBasket) {
                ((Employee) currentUser).addBonusPoints(((EmployeeBasket) currentBasket).calculateBonus());
            }

            if (currentUser instanceof LoyalCustomer && currentBasket instanceof LoyalCustomerBasket) {
                ((LoyalCustomer) currentUser).addBonusPoints(((LoyalCustomerBasket) currentBasket).calculateBonus());
            }

            currentBasket.pay();

            basketsPaid.add(currentBasket);
            basketServed.remove(currentBasket);

            currentBasket = null;

        } else{
            System.out.println(currentBundle.getString("not_successful_payment"));
        }

    }


}





