import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BinarySearch;

public class TestBinarySearch {
    public static void main( String[] args ) {
        int[] arr = new int[] {2, 3, 5, 7, 11, 13, 17, 19};
        
        StdOut.printf("Search 7 in the array: %d\n", BinarySearch.indexOf(arr, 7));
        StdOut.printf("Search 100 in the array: %d\n", BinarySearch.indexOf(arr, 100));
    }
}
