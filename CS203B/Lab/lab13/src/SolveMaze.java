import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.awt.Point;

public class SolveMaze {

    private static Graph buildGraph( char[][] maze ) {
        int height = maze.length;
        int width = maze[0].length;
        Graph graph = new GraphAdjList(height*width);
        int[] dh = new int[] { -1, 1,  0, 0 };
        int[] dw = new int[] {  0, 0, -1, 1 };

        for( int h = 0; h < height; h ++ )
            for( int w = 0; w < width; w ++ ) {
                if( maze[h][w] == 'W' )
                    continue;
                for( int i = 0; i < 4; i ++ ) {
                    int h2 = h+dh[i];
                    int w2 = w+dw[i];
                    if( h2 < 0 || h2 >= height || w2 < 0 || w2 >= width || maze[h2][w2] == 'W' )
                        continue;
                    graph.addEdge(h*width+w, h2*width+w2);
                }
            }
        return graph;
    }

    private static int findIndex( char[][] maze, char find ) {
        int height = maze.length;
        int width = maze[0].length;

        for( int h = 0; h < height; h ++ )
            for( int w = 0; w < width; w ++ )
                if( maze[h][w] == find )
                    return h*width+w;
        return -1;
    }

    private static boolean dfs( Graph graph, int current, int end, boolean[] visited ) {
        visited[current] = true;
        if( current == end )
            return true;
        for( Integer adj : graph.adjacency(current) ) {
            if( visited[adj] )
                continue;
            if( dfs(graph, adj, end, visited) )
                return true;
        }
        return false;
    }
    private static boolean dfs( Graph graph, int start, int end ) {
        boolean[] visited = new boolean[graph.size()];
        return dfs( graph, start, end, visited);
    }

    private static boolean bfs( Graph graph, int start, int end ) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        while( !queue.isEmpty() ) {
            int current = queue.poll();
            if( current == end )
                return true;
            for( int adj : graph.adjacency(current) ) {
                if( visited[adj] )
                    continue;
                visited[adj] = true;
                queue.add(adj);
            }
        }
        return false;
    }

    private static boolean bfs( char[][] maze ) {
        int height = maze.length;
        int width = maze[0].length;
        boolean[][] visited = new boolean[height][width];
        Queue<Point> queue = new LinkedList<>();
        // write your code here
        int[] dh = new int[] { -1, 1,  0, 0 };
        int[] dw = new int[] {  0, 0, -1, 1 };
        while( !queue.isEmpty() ) {
            Point current = queue.poll();
            int h = current.x;
            int w = current.y;
            if( maze[h][w] == 'E' )
                return true;
            for( int i = 0; i < 4; i ++ ) {
                int h2 = h+dh[i];
                int w2 = w+dw[i];
                // write your code here
                visited[h2][w2] = true;
                queue.add(new Point(h2,w2));
            }
        }
        return false;
    }

    public static void main( String[] args ) {
        try( Scanner input = new Scanner(new File("data/maze.txt")) ) {
            LinkedList<char[]> list = new LinkedList<>();
            while( input.hasNextLine() ) {
                String line = input.nextLine().trim();
                if( line.equals("") )
                    break;
                list.add(line.toCharArray());
            }
            char[][] maze = list.toArray(new char[0][]);
            System.out.println("Maze is:");
            for( int i = 0; i < maze.length; ++ i )
                System.out.println(maze[i]);

            int start = findIndex(maze, 'S');
            int end   = findIndex(maze, 'E');
            Graph graph = buildGraph(maze);
            //boolean result = dfs(graph, start, end);
            //boolean result = bfs(graph, start, end);
            boolean result = bfs(maze);
            System.out.printf("end is %sreachable from the start\n", (result) ? "" : "not " );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
