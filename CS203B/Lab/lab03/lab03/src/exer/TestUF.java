package exer;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestUF {
    
    static void test( In in1, In in2 ) {

        int N = in1.readInt();
        UnionFind uf = new UnionFind(N);
        int M = in1.readInt();

        Stopwatch sw = new Stopwatch();
        boolean right = true;
        for( int i = 0; i < M ; ++ i ) {
            int query = in1.readInt();
            int p = in1.readInt();
            int q = in1.readInt(); 

            if( query == 1 )
                uf.union(p, q);
            else {
                boolean isConnected = uf.isConnected(p, q);
                boolean answer = in2.readInt() == 1;
                if( isConnected != answer ) {
                    right = false;
                    break;
                }
            }
        }
        double t = sw.elapsedTime();

        StdOut.printf("%d data:  %s,  time:  %f\n", N, (right)?"right":"wrong", t);
        StdOut.printf("%s\n",  (right && t <= 5 )? "Pass!" : "Fail!");
    }
    public static void main( String[] args ) {
        for( int i = 1; i <= 16; ++ i ) {
            try {
                In fin1 = new In("./resources/data/"+i+".txt");
                In fin2 = new In("./resources/ans/"+i+".txt");
                test(fin1, fin2);
                fin1.close();
                fin2.close();
            } catch( IllegalArgumentException e ) {
                e.printStackTrace();
            }
        }
    }
}
