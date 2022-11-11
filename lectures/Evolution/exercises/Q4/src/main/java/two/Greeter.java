package two;

import java.util.Scanner;

public class Greeter {

    // prints: Nice to meet you Willy
    public static void askForFirstName() {

        askHelper(new String[] {"first name"} );

    }

    // prints: Nice to meet you Wonka
    public static void askForLastName() {

        askHelper(new String[] {"last name"});

    }

    // prints: Nice to meet you Willy Wonka
    public static void askForFullName() {

        askHelper(new String[] {"first name", "last name"});

    }

    // prints: Nice to meet you Willy Wonka (23 years old)
    public static void askForFullNameAndAge() {

        askHelper(new String[] {"first name", "last name", "age"});

    }

    // Type: duplicate code inside the same class

    // new helper method to avoid copying the read and writes from / to command line
    private static void askHelper(String[] ask) {

        Scanner s  = new Scanner(System.in);
        String result = "Nice to meet you";

        for (var property : ask) {

            System.out.println("Please enter your " + property + ":");

            if (property != "age") {
                result = result + " " + s.next();
            }
            else {
                result += result + " (" + s.next() + " years old)";
            }
        }
    }

    // SOLUTION: extract two methods; one for asking and one for printing the result. Use constants for the displayed
    // text

}