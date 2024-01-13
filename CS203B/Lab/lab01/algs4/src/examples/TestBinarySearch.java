package examples;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdArrayIO;

public class TestBinarySearch {
    public static void main( String[] args ) {

        StdOut.println("Test BinarySearch:");

        int[] arr = new int[] { 2, 3, 5, 7, 11, 13, 17, 19 };
        int target = 17;
        StdOut.println("Array is:");
        StdArrayIO.print(arr);
        StdOut.printf("Index of %d is %d.\n", target, BinarySearch.indexOf(arr, target));
    }
}
