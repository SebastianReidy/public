package one;

public class Benchpress extends Exercise {

    public Benchpress(int inWeight) {

        if (inWeight <= 0) {
            throw new IllegalArgumentException("Benchpress requires weight");

        }

        name = "bench press";
        weight = inWeight;

    }
}
