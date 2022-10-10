public class PeopleCounter {
    // EXERCISE: Design a "people counter" with TDD.
    // The counter:
    // - starts at 0
    // - has a configurable max
    // - can increment (+1)
    // - can reset (=0)

    private int count;
    private int max;

    public PeopleCounter(int max) {
        if (max < 0)
            throw new IllegalArgumentException("cannot initialize the counter maximum to a negative value");
        this.max = max;
        this.count = count;
    }

    public void increment(){
        if (this.count == this.max)
            throw new RuntimeException("reached max");
        else
            this.count++;
    }

    public int getCount(){
        return this.count;
    }

    public void reset(){
        this.count = 0;
    }
}
