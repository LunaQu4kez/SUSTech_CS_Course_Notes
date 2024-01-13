package examples;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class TestStdDraw {
    public static void main(String[] args) {

        StdOut.println("Test StdDraw:");

        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(0.0, 0.0);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.point(1.0, 0.0);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0.0, 1.0);

        StdDraw.line(0.2, 0.2, 0.8, 0.2);
        StdDraw.rectangle(0.5, 0.5, 0.2, 0.2);
    }
}
