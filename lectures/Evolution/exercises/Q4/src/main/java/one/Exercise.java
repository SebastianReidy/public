package one;

public class Exercise {
    String name;
    int weight;

    public Exercise() {
        name = "";
        weight = 0;
    }

    public boolean requiresWeights() {
        return weight != 0;
    }

    public int getWeight() {return weight; }

    public void doExercise() {
        System.out.println("Wow, I totally just did a " + name);
    }
}
// Type: sibling classes having all the same methods
// Solution: move everything into parent class

// put all methods in the parent class

// SOLUTION: make exercise an abstract class, implement two subclasses for weighted and unweighted exercises

// Refactoring method: pull up class, pull up exercise