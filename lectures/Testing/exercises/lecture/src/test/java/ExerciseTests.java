import org.junit.jupiter.api.Test;

import java.net.PortUnreachableException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ExerciseTests {
    @Test
    void onePlusOneIsTwo() {
        assertThat(1 + 1, is(2));
    }

    @Test
    void minusOne() {
        assertThrows(IllegalArgumentException.class, () -> Functions.fibonacci(-1));
    }

    @Test
    void secondFibonacci() {
        assertThat(Functions.fibonacci(2), is(1));
    }

    @Test
    void twelfthFibonacci(){
        assertThat(Functions.fibonacci(12), is(144));
    }

    @Test
    void separateByS(){
        ArrayList<String> result = new ArrayList<String>();
        result.add("Hello");
        result.add("World");
        assertThat(result, is(Functions.splitString("HellosWorld", 's')));
    } // could also use assert that and contains

    @Test
    void containsGiven(){
        var toTest = new String[] {"1","2","3"};
        Functions.shuffle(toTest);
        assertThat(toTest, arrayContainingInAnyOrder("1","2","3"));
        // TODO: write a test checking the use of given characters
        // hamcrest arraycontaininginanyorder
    }

    @Test
    void startAtZero(){
        PeopleCounter count = new PeopleCounter(99);
        assertThat(count.getCount(), is(0));
    }

    @Test
    void incrementCounter(){
        PeopleCounter count = new PeopleCounter(4);
        count.increment();
        assertThat(count.getCount(), is(1));
    }

    @Test
    void resetCounter(){
        PeopleCounter count = new PeopleCounter(5);
        count.increment();
        count.reset();
        assertThat(count.getCount(), is(0));
    }

    @Test
    void ExceptionIfExceedingMax(){
        PeopleCounter count = new PeopleCounter(1);
        count.increment();
        assertThrows(RuntimeException.class, () -> count.increment());
    }

    @Test
    void NegativeMaximum(){
        assertThrows(IllegalArgumentException.class, () -> new PeopleCounter(-1));
    }

    // TODO: tests!
    // Remember "assertThat" with matchers (see the documentation!)
    // and "assertThrows" to assert an exception is thrown
}
