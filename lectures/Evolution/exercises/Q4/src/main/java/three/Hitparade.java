package three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hitparade {

    // sorry if your favorite song didn't make the cut, you can add it if you want!
    private final static List<Item> songs = Arrays.asList(
        new Item("Never Gonna Give You Up", "Rick Astley", 8),
        new Item("Friday", "Rebecca Black", 1),
        new Item("CHIHUAHUA", "DJ BoBo", 5),
        new Item("Bohemian Rhapsody", "Queen", 5),
        new Item("Take On Me", "A-ha", 7),
        new Item("Africa", "Toto", 10),
        new Item("Gucci Gang", "Lil Pump", 5),
        new Item("Gangnam Style", "PSY", 5)
    );

    private static final int N = 3;

    Rating rating = new Rating(songs, N, "SONGS");

    // prints the top N most popular songs in a nice way
    public void prettyPrintMostPopularSongs() {

        rating.prettyPrintMostPopularItems();

    }
}
