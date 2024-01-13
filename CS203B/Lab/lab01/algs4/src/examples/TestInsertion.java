package examples;

import java.util.Random;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

public class TestInsertion {
    private static void printArray( Double[] arr ) {
        StdOut.println(arr.length);
        for( Double d : arr )
            StdOut.printf("%9.5f ", d);
        StdOut.println();
    }
    public static void main( String[] args ) {

        StdOut.println("Test Insertion sort:");

        Double[] arr = new Double[10];
        Random rand = new Random();
        for( int i = 0; i < arr.length; ++ i )
            arr[i] = rand.nextDouble();

        StdOut.println("Array is:");
        printArray(arr);

        Insertion.sort(arr);
        StdOut.println("After Insertion sort:");
        printArray(arr);
    }
}
