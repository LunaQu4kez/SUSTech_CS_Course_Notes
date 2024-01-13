import java.util.LinkedList;

public class GraphAdjList implements Graph {

    private int num;
    private LinkedList<Integer>[] adjList;

    @SuppressWarnings (value="unchecked")
    public GraphAdjList( int verticesNumber ) {
        num = verticesNumber;
        adjList = new LinkedList[num];
        for( int i = 0; i < adjList.length; i ++ )
            adjList[i] = new LinkedList<>();
    }

    public int size() {
        return num;
    }

    public void addEdge(int v1, int v2) {
        if( adjList[v1].contains(v2) )
            return;
        adjList[v1].add(v2);
    }

    @SuppressWarnings (value="unchecked")
    public Iterable<Integer> adjacency(int v) {
        return (LinkedList<Integer>)adjList[v].clone();
    }

    public boolean hasEdge(int v1, int v2) {
        return adjList[v1].contains(v2);
    }

    public static void main( String[] args ) {
        Graph graph = new GraphAdjList(6);
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
