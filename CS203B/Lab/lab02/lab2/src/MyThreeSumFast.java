import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class MyThreeSumFast {

    // Do not instantiate.
    private MyThreeSumFast() { }

    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int[] k = BinaryRangeSearch.binarySearch(a, -(a[i] + a[j]), j+1, n-1);
                if (k[0] > -1) count+= k[1]-k[0]+1;
            }
        }
        return count;
    } 

    public static void main( String[] args ) {
        for( int i = 1; i <= 8; ++ i ) {
            try {

                In fin = new In("./resources/data/"+i+"Kints.txt");
                int[] arr = fin.readAllInts();
                fin.close();

                StdOut.printf("Calculating sums in %dKints.txt:", i);
                Stopwatch timer = new Stopwatch();
                int count = MyThreeSumFast.count(arr);
                
                StdOut.printf(" size of data: %d, result: %d sums, time spent: %f seconds.\n", arr.length, count, timer.elapsedTime());


            } catch( IllegalArgumentException e ) {
                e.printStackTrace();
            }
        }
        StdOut.println(MyThreeSumFast.count(new int[]{-2, -2, 1, 1, 1, 1}));
    }
} 
