import java.util.Arrays;

import edu.princeton.cs.algs4.Quick;

public class TestQuickSort {
    public static void main( String[] args ) {

        Integer[] array = new Integer[] { 7, 6, 5, 4, 3, 2, 1, 10, 9, 8 };

        Quick.sort(array);

        System.out.println("\nAfter quicksort, the array is:");
        System.out.println(Arrays.toString(array));
    }
}
