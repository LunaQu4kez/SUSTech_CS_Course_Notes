import java.util.Arrays;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ThreeSumN2 {
    
    private ThreeSumN2() {}

    static interface PairHandler {
        void handle( int i, int j );
    }

    private static boolean sortContainsDuplicates( int [] arr ) {
        Arrays.sort(arr);
        for( int i = 1; i < arr.length; ++ i )
            if( arr[i] == arr[i-1] )
                return true;
        return false;
    }

    private static void twoSum( int[] arr, int lo, int hi, int target, PairHandler handler ) {
        while( lo < hi ) {
            if( arr[lo] + arr[hi] == target ) {
                handler.handle(lo, hi);
                lo++;
                hi--;
            } else if( arr[lo] + arr[hi] < target ) {
                lo++;
            } else {
                hi--;
            }
        }
    }

    private static class PrintHandler implements PairHandler {
        int arr[];
        int first;
        public PrintHandler( int[] array, int first ) {
            this.arr = array;
            this.first = first;
        }
        @Override
        public void handle(int i, int j) {
            System.out.println(arr[first] + " " + arr[i] + " " + arr[j]);
        }
    }

    public static void printAll( int[] arr ) {
        if( sortContainsDuplicates(arr) )
            throw new IllegalArgumentException("array contains duplicate integers");
        for( int i = 0; i < arr.length; ++ i ) {
            twoSum(arr, i+1, arr.length-1, -arr[i], new PrintHandler(arr, i));
        }
    }

    private static class CountHandler implements PairHandler {
        int count;
        public CountHandler() { count = 0; }
        @Override
        public void handle(int i, int j) {
            count ++;
        }
    }

    public static int count(int[] arr) {
        if (sortContainsDuplicates(arr))
            throw new IllegalArgumentException("array contains duplicate integers");
        CountHandler handler = new CountHandler();
        for (int i = 0; i < arr.length; i++) {
            twoSum(arr, i+1, arr.length-1, -arr[i], handler);
        }
        return handler.count;
    } 

    public static void main( String[] args ) {
        for( int i = 1; i <= 8; ++ i ) {
            try {

                In fin = new In("./resources/data/"+i+"Kints.txt");
                int[] arr = fin.readAllInts();
                fin.close();

                StdOut.printf("Calculating sums in %dKints.txt:", i);
                Stopwatch timer = new Stopwatch();
                int count = ThreeSumN2.count(arr);
                
                StdOut.printf(" size of data: %d, result: %d sums, time spent: %f seconds.\n", arr.length, count, timer.elapsedTime());
            } catch( IllegalArgumentException e ) {
                e.printStackTrace();
            }
        }
    }
}
