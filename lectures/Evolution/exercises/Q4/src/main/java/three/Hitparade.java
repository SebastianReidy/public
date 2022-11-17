package three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * instance of duplication in different classes
 */
public class Hitparade{

    // sorry if your favorite song didn't make the cut, you can add it if you want!
    private final static List<Song> songs = Arrays.asList(
        new Song("Never Gonna Give You Up", "Rick Astley", 8),
        new Song("Friday", "Rebecca Black", 1),
        new Song("CHIHUAHUA", "DJ BoBo", 5),
        new Song("Bohemian Rhapsody", "Queen", 5),
        new Song("Take On Me", "A-ha", 7),
        new Song("Africa", "Toto", 10),
        new Song("Gucci Gang", "Lil Pump", 5),
        new Song("Gangnam Style", "PSY", 5)
    );

    private static final int N = 3;

    private static final Comparison<Song> comparison = new Comparison<Song>();

    // prints the top N most popular beers in a nice way
    public static void prettyPrintMostPopularBeers() {
        comparison.prettyPrintMostPopular("TOP SONGS", songs,N);
    }

    // other methods like:
    // getMostTrendingArtists()
    // getTopSellingRockAlbum()
    // ...


    public static class Song implements Comparable<Song> {
        String name;
        String artist;
        int popularity;

        Song(String name, String artist, int popularity) {
            this.name = name;
            this.artist = artist;
            this.popularity = popularity;
        }

        @Override
        public int compareTo(Song o) {
            return Integer.compare(o.popularity, this.popularity);
        }

        @Override
        public String toString() {
            return artist + " - " + name;
        }
    }
}
