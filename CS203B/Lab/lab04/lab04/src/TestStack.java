import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class TestStack {
    public static void main( String[] args ) {
        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        stack.push(1);
        stack.push(2);

        StdOut.println("Iterate all elements in Stack:");
        for( Integer i : stack ) StdOut.print(" "+i);

        StdOut.printf("\n\nPop an element: %d\n", stack.pop());

        StdOut.println("\nAfter pop, the stack is:");
        for( Integer i : stack ) StdOut.print(" "+i);
        StdOut.println();
    }
}
