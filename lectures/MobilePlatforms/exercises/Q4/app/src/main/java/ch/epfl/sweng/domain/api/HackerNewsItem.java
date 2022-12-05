package ch.epfl.sweng.domain.api;

public class HackerNewsItem {
    public int id;
    public boolean deleted;
    public String type;
    public String by; // username of the author
    public long time;
    public String text;
    public int parent;
    public int[] kids;
    public String url;
    public int score;
    public String title;
    public int[] parts;
    public int descendants;
}
