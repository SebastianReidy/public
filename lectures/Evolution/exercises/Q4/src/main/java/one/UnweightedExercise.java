package one;

public class UnweightedExercise extends Exercise{

    UnweightedExercise(String name){
        super(name);
    }

    public boolean requiresWeights() {
        return false;
    }

}
