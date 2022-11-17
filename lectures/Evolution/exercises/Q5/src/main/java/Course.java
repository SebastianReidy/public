public class Course {
    public enum TYPE {
        MATH, ART, ENGLISH, HISTORY, GEOGRAPHY
    }

    static private TYPE type;

    public Course(TYPE type) {
        this.type = type;
    }

    public static TYPE getType() {
        return type;
    }
}