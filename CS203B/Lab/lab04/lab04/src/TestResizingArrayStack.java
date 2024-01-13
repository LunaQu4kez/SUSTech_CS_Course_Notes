import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.StdOut;

public class TestResizingArrayStack {
    public static void main( String[] args ) {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();

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
