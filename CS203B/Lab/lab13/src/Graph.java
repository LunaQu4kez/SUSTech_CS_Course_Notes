public interface Graph {
    /**
     * @return number of vertices.
     */
    int size();

    /**
     * add an edge between v1 and v2, where 0 <= v1, v2 <= size()-1
     * @param v1
     * @param v2
     */
    void addEdge( int v1, int v2 );

    /**
     * @return all vertices that vertex v is connected to.
     */
    Iterable<Integer> adjacency( int v );

    /**
     * @return whether there is an edge from v1 to v2.
     */
    boolean hasEdge( int v1, int v2 );
}