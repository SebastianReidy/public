package three;

import java.util.*;

public class Satellite{
    // ratings are subjective, sorry if your favorite beer didn't make the cut, you can add it if you want!
    private final static List<Item> beers = Arrays.asList(
        new Item("Cuvée des Trolls", 8),
        new Item("Lupulus", 9),
        new Item("Kwak", 10),
        new Item("Punk IPA", 7),
        new Item("Gurten", 5),
        new Item("Wittekop", 4),
        new Item("Brooklyn Lager", 6),
        new Item("CoCo POW!", 0)
    );

    private static final int N = 5;

    Rating rating = new Rating(beers, N, "BEERS");

    // prints the top N most popular beers in a nice way
    public void prettyPrintMostPopularBeers() {

        rating.prettyPrintMostPopularItems();

    }

    // Type: duplicate code in two different classes

    // Added two general classes Rating and Item to move the shared code between Satellite and Hitparade to those classes.

    // SOLUTION: only extract one class for the topNcomparison

}
