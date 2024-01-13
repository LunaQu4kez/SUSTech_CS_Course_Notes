import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdOut;

public class TestMergeSort {
    public static void main( String[] args ) {

        Integer[] array = new Integer[] { 7, 6, 5, 4, 3, 2, 1, 10, 9, 8 };

        Merge.sort(array);

        StdOut.println("\nAfter mergesort, the array is:");
        for( Integer i : array ) StdOut.print(" "+i);
        StdOut.println();
    }
}
