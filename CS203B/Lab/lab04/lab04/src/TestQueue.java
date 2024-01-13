import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class TestQueue {
    public static void main( String[] args ) {
        Queue<Integer> queue = new Queue<>();

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);

        StdOut.println("Iterate all elements in Queue:");
        for( Integer i : queue ) StdOut.print(" "+i);

        StdOut.printf("\n\nDequeue an element: %d\n", queue.dequeue());

        StdOut.println("\nAfter dequeue, the queue is:");
        for( Integer i : queue ) StdOut.print(" "+i);
        StdOut.println();
    }
}
