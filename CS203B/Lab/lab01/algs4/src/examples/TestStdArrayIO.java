package examples;

import edu.princeton.cs.algs4.StdArrayIO;
import edu.princeton.cs.algs4.StdOut;

public class TestStdArrayIO {
    public static void main( String[] args ) {

        StdOut.println("Test StdArrayIO:");

        StdOut.println("Read 1D int array from input:");
        int[] intArr = StdArrayIO.readInt1D();
        StdArrayIO.print(intArr);

        StdOut.println("Read 2D int array from input:");
        int[][] intArr2 = StdArrayIO.readInt2D();
        StdArrayIO.print(intArr2);

        StdOut.println("Read 2D double array from input:");
        double[][] douArr2 = StdArrayIO.readDouble2D();
        StdArrayIO.print(douArr2);

        StdOut.println("Read 2D boolean array from input:");
        boolean[][] boolArr2 = StdArrayIO.readBoolean2D();
        StdArrayIO.print(boolArr2);
    }
}
