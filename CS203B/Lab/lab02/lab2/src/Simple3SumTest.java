
import edu.princeton.cs.algs4.ThreeSum;
import edu.princeton.cs.algs4.StdOut;

public class Simple3SumTest {
    
    public static void main( String[] args ) {
        int[] array = new int[]{30, -40, -20, -10, 40, 0, 10, 5};
        int count = ThreeSum.count(array);
        if( count <= 1 )
            StdOut.printf("There is %d triple in the array.\n", count);
        else
            StdOut.printf("There are %d triples in the array.\n", count);
    }
}