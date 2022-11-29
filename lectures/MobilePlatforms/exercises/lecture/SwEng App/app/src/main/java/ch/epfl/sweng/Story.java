package ch.epfl.sweng;

public class Story {

    public final int id;
    public final String title;
    public final String url;

    public Story(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    // Don't forget to implement equals() and hashCode()!

    public Boolean equals(Story story){
        return this.id == story.id;
    }
}