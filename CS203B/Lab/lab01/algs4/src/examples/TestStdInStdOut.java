package examples;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * An example of basic usages of StdIn class and StdOut class.
 */
public class TestStdInStdOut {
    public static void main( String[] args ) {

        StdOut.println("Test StdIn and StdOut:");

        int i = StdIn.readInt();
        StdOut.print("Read int from input:");
        StdOut.println(i);

        String str = StdIn.readString();
        StdOut.print("Read String from input:");
        StdOut.println(str);

        String line = StdIn.readLine();
        StdOut.print("Read line from input:");
        StdOut.println(line);

        double[] darr = StdIn.readAllDoubles();
        StdOut.print("Read all doubles from input:");
        for( double d : darr )
            StdOut.print(d+" ");
        StdOut.println();

    }
}
