package three;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rating {

    private final List<Item> items;
    private final int n;
    private static String capsName;

    protected Rating(List<Item> items, int n, String capsName) {

        if (n <= 0) {
            throw new IllegalArgumentException("N must be positive");
        }

        this.items = items;
        this.n = n;
        this.capsName = capsName;

    }

    // returns the top N most popular items
    private List<Item> getMostPopularItems() {
        List<Item> allItems = new ArrayList<Item>(items);
        Collections.sort(allItems);
        return allItems.subList(0, n);
    }

    // prints the top N most popular items in a nice way
    public void prettyPrintMostPopularItems() {
        List<Item> topItems = getMostPopularItems();
        System.out.println("====TOP " + capsName + "====");
        int i = 1;
        for (Item b : topItems)
            System.out.println(i++ + ") " + b);
    }

}
