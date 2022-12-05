package one;

public abstract class WeightedExercise extends Exercise {
    private int weight;

    public WeightedExercise(String name, int weight) {
        super(name);
        this.weight = weight;
    }

    public boolean requiresWeights() {
        return true;
    }

    public int getWeight() {
        return weight;
    }

}
