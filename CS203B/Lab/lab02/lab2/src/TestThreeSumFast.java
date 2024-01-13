
import edu.princeton.cs.algs4.ThreeSumFast;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestThreeSumFast {
    
    public static void main( String[] args ) {
        for( int i = 1; i <= 8; ++ i ) {
            try {

                In fin = new In("./resources/data/"+i+"Kints.txt");
                int[] arr = fin.readAllInts();
                fin.close();

                StdOut.printf("Calculating sums in %dKints.txt:", i);
                Stopwatch timer = new Stopwatch();
                int count = ThreeSumFast.count(arr);
                
                StdOut.printf(" size of data: %d, result: %d sums, time spent: %f seconds.\n", arr.length, count, timer.elapsedTime());
            } catch( IllegalArgumentException e ) {
                e.printStackTrace();
            }
        }
    }
}
