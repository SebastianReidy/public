import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class QuicksortTest {

  @Test
  public void testAddition() {
    assertThat(1 + 1, Matchers.is(2));
  }

  @Test
  public void SortEmptyArray() {
    Integer[] array = new Integer[] {};
    Integer[] arraySorted = new Integer[] {};
    Quicksort.sort(array, Comparator.naturalOrder());
    assertArrayEquals(arraySorted, array);
  }
  @Test
  public void SortNullArrayThrowsException() {
    Integer[] nullArray = null;
    assertThrows(IllegalArgumentException.class, () -> Quicksort.sort(nullArray, Comparator.naturalOrder()));
  }

  @Test
  public void SortArrayOfIntegers() {
    Integer[] array = new Integer[] {4,3,7,2,7};
    Integer[] arraySorted = new Integer[] {2,3,4,7,7};
    Quicksort.sort(array, Comparator.naturalOrder());
    assertArrayEquals(arraySorted, array);
  }
}
