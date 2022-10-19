import java.util.Comparator;

/**
 * A class containing a static method to sort an array of generic elements using the quicksort
 * algorithm.
 */
public class Quicksort {

  private Quicksort() {
    // Not instantiable.
  }

  /**
   * Sorts the given array using the quicksort algorithm.
   *
   * @param elements the array to sort
   * @param <T>      the type of the array elements
   */
  public static <T> void sort(T[] elements, Comparator<T> comparator) {

    if(elements == null)
      throw new IllegalArgumentException("Array cannot be null");

    sort(elements, comparator, 0, elements.length);
  }

  // Sorts the elements in the given range.
  private static <T> void sort(T[] elements, Comparator<T> comparator, int from, int until) {
    if (from >= until) { // from == until is sorted
      return;
    }
    var pivot = partition(elements, comparator, from, until);
    sort(elements, comparator, from, pivot);
    sort(elements, comparator, pivot + 1, until); // pivot element is at the right position
  }

  // Partitions the elements in the given range around a pivot, and returns its index.
  private static <T> int partition(T[] elements, Comparator<T> comparator, int from, int until) {
    var p = elements[from];
    var s = until;
    for (var i = until - 1; i > from; i--) {
      if (comparator.compare(elements[i], p) > 0) {
        s--;
        swap(elements, i, s);
      }
    }
    swap(elements, from, s-1); // corrected
    return s - 1;
  }

  // Swaps the elements at the given indices.
  private static <T> void swap(T[] elements, int i, int j) {
    var tmp = elements[i];
    elements[i] = elements[j];
    elements[j] = tmp;
  }
}
