package two;

import java.util.Scanner;

/**
 * instance of duplication in the same class
 */
public class Greeter {

    // prints: Nice to meet you Willy
    public static void askForFirstName() {
        String firstName = getInput("first name");
        greet(firstName);
    }

    // prints: Nice to meet you Wonka
    public static void askForLastName() {
        String lastName = getInput("last name");
        greet(lastName);
    }

    // prints: Nice to meet you Willy Wonka
    public static void askForFullName() {
        String firstName = getInput("first name");
        String lastName = getInput("last name");
        greet(firstName + " " + lastName);
    }

    // prints: Nice to meet you Willy Wonka (23 years old)
    public static void askForFullNameAndAge() {
        String firstName = getInput("first name");
        String lastName = getInput("last name");
        String age = getInput("age");
        greet(firstName + " " + lastName + " (" + age + " years old)");
    }

    private static String getInput(String name){
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your " + name + ":");
        return s.next();
    }

    private static void greet(String identity){
        System.out.println("Nice to meet you " + identity);
    }
}