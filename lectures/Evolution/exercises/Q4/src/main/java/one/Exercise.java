package one;

/**
 * The issue is duplication in sibling classes
 */
public class Exercise {
    String name;
    public Exercise(String name) {
        this.name = name;
    }

    public void doExercise() {
        System.out.println("Wow, I totally just did a " + name);
    }
}
