import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;

public class TestMaxPQ {
    public static void main( String[] args ) {

        MaxPQ<Integer> pq = new MaxPQ<>(20);

        pq.insert(1);
        pq.insert(3);
        pq.insert(2);

        while( pq.size() > 0 ) {
            StdOut.println(pq.delMax());
        }
    }
}
