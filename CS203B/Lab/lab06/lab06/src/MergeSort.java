import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    private static void insertion( int[] array, int low, int high ) {
        for( int i = low+1; i <= high; ++ i ) {
            int tmp = array[i];
            int j;
            for( j = i; j > low && array[j-1] > tmp; -- j )
                array[j] = array[j-1];
            array[j] = tmp;
        }
    }
    private static void merge( int[] array, int low, int mid, int high, int [] aux ) {
        System.arraycopy(array, low, aux, low, high-low+1);
        int i, j1, j2;
        for( i = low, j1=low, j2 = mid+1; j1<=mid && j2 <= high; ) {
            if( aux[j1] <= aux[j2] )
                array[i++] = aux[j1++];
            else
                array[i++] = aux[j2++];
        }
        while( j1 <= mid )
            array[i++] = aux[j1++];
        while( j2 <= high )
            array[i++] = aux[j2++];
    }
    private static void sort( int[] array, int low, int high, int[] aux ) {
        if( high - low < 10 ) {
            insertion(array, low, high);
            return;
        }
        int mid = low + (high-low)/2;
        sort( array, low, mid, aux );
        sort( array, mid+1, high, aux );
        merge(array, low, mid, high, aux);
    }
    public static void sort( int[] array ) {
        sort(array, 0, array.length-1, new int[array.length]);
    }

    private static boolean test( int[] array ) {
        int[] array2 = new int[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        Arrays.sort(array);
        sort(array2);
        return Arrays.equals(array, array2);
    }
    public static void main( String[] args ) {
        Random rand = new Random();
        boolean result = test(new int[]{1, 2, 3, 4, 5});
        result = result && test(new int[]{0, 0, 0, 0,});
        result = result && test(new int[0]);
        int[] array = new int[1000];
        for( int ite = 0; ite < 1000; ++ ite ) {
            for( int i = 0; i < array.length; ++ i )
                array[i] = rand.nextInt();
            result = result && test(array);
        }
        System.out.println(result);
    }
}
