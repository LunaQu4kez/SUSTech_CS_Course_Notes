import java.util.LinkedList;

public class GraphAdjacency implements Graph {

    private int num;
    private boolean[][] adj;

    public GraphAdjacency( int verticesNumber ) {
        num = verticesNumber;
        adj = new boolean[num][num];
    }

    public int size() {
        return num;
    }

    public void addEdge(int v1, int v2) {
        adj[v1][v2] = true;
    }

    public Iterable<Integer> adjacency(int v) {
        LinkedList<Integer> list = new LinkedList<>();
        for( int i = 0; i < num; i ++ )
            if( adj[v][i] )
                list.add(i);
        return list;
    }

    public boolean hasEdge(int v1, int v2) {
        return adj[v1][v2];
    }

    public static void main( String[] args ) {
        Graph graph = new GraphAdjacency(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 0);
        for( Integer i : graph.adjacency(0) )
            System.out.print(i+" ");
        System.out.println();
        System.out.println(graph.hasEdge(0, 2));
        System.out.println(graph.hasEdge(0, 3));
    }
}
