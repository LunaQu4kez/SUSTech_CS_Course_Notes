import java.util.Random;

public class InsertionSort {

    private static void exchange( int[] array, int i, int j ) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void sort( int[] array ) {
        for( int i = 1; i < array.length; ++ i ) {
            for( int j = i; j > 0 && array[j] <= array[j-1]; -- j )
                exchange(array, j-1, j);
        }
    }

    public static void main( String[] args ) {

        Random rand = new Random();

        for( int i = 100; i < 1000000; i*=2 ) {
            int[] array = new int[i];
            for( int j = 1; j < array.length; ++ j )
                array[j] = (rand.nextDouble() < 0.9) ? (array[j-1]+1) : (array[j-1]-1);
            
            long t1 = System.currentTimeMillis();
            sort(array);
            long t2 = System.currentTimeMillis();
            System.out.printf("Data size: %d, time: %d.\n", i, t2-t1);
        }
    }
}
