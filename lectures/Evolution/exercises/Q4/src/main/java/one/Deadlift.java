package one;

public class Deadlift extends Exercise {

    public Deadlift(int inWeight) {

        if (inWeight <= 0) {
            throw new IllegalArgumentException("Deadlift requires weight");
        }

        name = "deadlift";
        this.weight = inWeight;

    }
}
