package examples;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.DepthFirstSearch;
import edu.princeton.cs.algs4.StdOut;

public class TestDFS {
    public static void main( String[] args ) {

        StdOut.println("Test Depth First Search:");

        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);

        StdOut.println("Source node is 0.");
        for( int i = 0; i < 4; ++ i )
            StdOut.printf("vertice %d reachable from source: %b\n", i, dfs.marked(i));
    }
}
