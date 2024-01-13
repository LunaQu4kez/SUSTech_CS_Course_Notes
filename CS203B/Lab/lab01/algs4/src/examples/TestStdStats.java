package examples;

import java.util.Random;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdArrayIO;

public class TestStdStats {
    public static void main( String[] args ) {

        StdOut.println("Test StdStats:");

        double[] arr = new double[10];
        Random rand = new Random();
        for( int i = 0; i < arr.length; ++ i )
            arr[i] = rand.nextDouble();

        StdOut.println("Array is:");
        StdArrayIO.print(arr);

        StdOut.printf("Max of array: %f\n", StdStats.max(arr));
        StdOut.printf("Average of array: %f\n", StdStats.mean(arr));
    }
}
