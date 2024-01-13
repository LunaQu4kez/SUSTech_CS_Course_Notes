package examples;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdArrayIO;

public class TestStdRandom {
    public static void main( String[] args ) {

        StdOut.println("Test StdRandom:");

        StdOut.println("Sample 0, 1, 2 with probability 0.25, 0.25, 0.5:");
        double[] probability = new double[]{0.25, 0.25, 0.5};
        for( int i = 0; i < 10; ++ i )
            StdOut.print(StdRandom.discrete(probability)+" ");
        StdOut.println();

        StdOut.println("Sample from standard Gaussian distribution:");
        for( int i = 0; i < 10; ++ i )
            StdOut.print(StdRandom.gaussian()+" ");
        StdOut.println();

        StdOut.println("Randomly shuffle an array:");
        int[] arr = new int[]{ 2, 3, 5, 7, 11, 13, 17, 19 };
        StdOut.println("Before shuffle:");
        StdArrayIO.print(arr);
        StdRandom.shuffle(arr);
        StdOut.println("After shuffle:");
        StdArrayIO.print(arr);
    }
}
