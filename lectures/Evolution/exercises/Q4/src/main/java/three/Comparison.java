package three;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comparison<T extends Comparable<T>> {

    private List<T> getMostPopular(List<T> items, int n) {
        List<T> allItems = new ArrayList<>(items);
        Collections.sort(allItems);
        return allItems.subList(0, n);
    }

    // prints the top N most popular songs in a nice way
    public void prettyPrintMostPopular(String name, List<T> allItems, int n) {
        List<T> topItems = getMostPopular(allItems, n);
        System.out.println("====" + name + "====");
        int i = 1;
        for (T s : topItems)
            System.out.println(i++ + ") " + s);
    }
}
