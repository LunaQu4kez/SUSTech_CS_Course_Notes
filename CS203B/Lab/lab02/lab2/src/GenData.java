import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import edu.princeton.cs.algs4.StdRandom;

public class GenData {
    public static void main( String[] args ) {
        int[] arr = new int[16000];
        for( int i = 0, num = -8000; i < arr.length; ++i, num++ )
            arr[i] = num;
        StdRandom.shuffle(arr);

        new File("./resources/data/").mkdirs();

        for( int i = 1; i <= 8; ++ i ) {
            try ( PrintWriter fout = new PrintWriter("./resources/data/"+i+"Kints.txt") ) {
                for( int j = 0; j < i*1000 && j < arr.length; ++ j )
                    fout.println(" " + arr[j]);
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }

    }
}