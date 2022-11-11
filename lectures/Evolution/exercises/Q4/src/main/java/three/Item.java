package three;

public class Item implements Comparable<Item>{

    String name;
    String secondName;
    int popularity;

    Item(String name, int popularity){

        this.name = name;
        this.popularity = popularity;
        this.secondName = "";

    }

    Item(String name, String secondName, int popularity){

        this.name = name;
        this.popularity = popularity;
        this.secondName = secondName;

    }

    @Override
    public int compareTo(Item o) {
        return Integer.compare(o.popularity, this.popularity);
    }

    @Override
    public String toString() {

        if (secondName == "") {
            return name;
        }
        else {
            return secondName + " - " + name;
        }
    }
}
