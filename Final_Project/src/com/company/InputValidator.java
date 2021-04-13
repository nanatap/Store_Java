package com.company;

import java.util.Scanner;

public class InputValidator {

    public static int inputInt(int max) {
        Scanner userInput = new Scanner(System.in);

        int i = -1;

        while (i <= 0 || i > max) {

            try {

                i = Integer.parseInt(userInput.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Wrong type of input. Please enter positive integer");
                continue;
            }

            if (i < 1) {
                System.out.println("Please retry with a valid positive integer.");
            }

            if (i > max) {
                System.out.println("Please enter positive integer which is less than " + max);
            }

        }

        return i;
    }

    public static double inputDouble() {
        Scanner userInput = new Scanner(System.in);

        double i = -1;

        while (i <= 0) {

            try {

                i = Double.parseDouble(userInput.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Wrong type of input. Please enter positive double");
                continue;
            }

        }

        return i;
    }

    public static String inputString() {
        Scanner userInput = new Scanner(System.in);

        return userInput.nextLine();
    }

}
