package examples;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

public class TestRedBlackBST {
    public static void main( String[] args ) {

        StdOut.println("Test Red Black Tree:");

        RedBlackBST<Integer, String> rbt = new RedBlackBST<>();

        StdOut.println("Insert some vertices in the tree:");
        rbt.put(1, "One");
        rbt.put(2, "Two");
        rbt.put(3, "Three");

        StdOut.println("After insertion, the tree contains:");
        for( Integer i : rbt.keys() )
            StdOut.printf("%3d:%s\n", i, rbt.get(i));
    }
}
