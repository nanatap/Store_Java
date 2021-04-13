package com.company.payment;
import java.time.LocalDate;
public class PaymentProvider {

    private String cardNumber;
    private int currentMonth;
    private int currentYear;
    private String cvv;
    LocalDate date = LocalDate.now();


    public  PaymentProvider(){

    }
    public PaymentProvider(String cardNumber, int currentMonth, int currentYear, String  cvv) {
        this.cardNumber = cardNumber;
        this.currentMonth = currentMonth;
        this.currentYear =currentYear;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public String getCvv() {
        return cvv;
    }

    public boolean cardValidation(){
        if(this.validateCardNumber() && validateCurrentMonth() && validateCurrentYear() && validateCvv()){
            System.out.println("Card details are valid. You can continue your payment.");
            return true;
        }
        return false;
    }


    private boolean validateCardNumber() {

        int first = cardNumber.charAt(0) - '0';

        if(cardNumber.length() != 16 || first == 0 ){
            return false;
        }

        // ToDo fix card

        int addAll = 0;
        String substring = cardNumber.substring(cardNumber.length() - 2);
        StringBuilder sb = new StringBuilder(substring);
        String reversedArray = sb.reverse().toString();
        String modifiedArray = "";

        for(int i = 0; i <reversedArray.length(); i++){
             int x = reversedArray.charAt(i) - '0';

            if(i % 2 != 0 ){
                x = x * 2;
            }
            if(x > 9){
                x = x - 9;
            }

            addAll += x;

        }
        int lastDigit = cardNumber.charAt(cardNumber.length() - 1) - '0';
//        if(addAll % 10 == lastDigit){
//            System.out.println("Card number is valid!");
//            return true;
//        }else
//            System.out.println("Invalid card number");
//        return false;

        return true;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private boolean validateCurrentMonth() {

        return this.getCurrentMonth() > 0 && this.getCurrentMonth() < 13;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    private boolean validateCurrentYear() {
        return date.getYear() < this.currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    private boolean validateCvv() {

        if(this.cvv.length() > 3){
            System.out.println("Invalid CVV");
            return false;
        }
        return true;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "PaymentProvider{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + currentMonth + '\'' +
                ", expirationDate='" + currentYear + '\'' +

                ", cvv=" + cvv +
                '}';
    }
}