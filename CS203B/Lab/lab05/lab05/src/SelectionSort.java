import java.util.Random;

public class SelectionSort {

    private static <E> void exchange( E[] array, int i, int j ) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static <E extends Comparable<E>> void sort( E[] array ) {
        for( int i = 0; i < array.length; ++ i ) {
            int min = i;
            for( int j = i+1; j < array.length; ++ j ) {
                int compare = array[min].compareTo(array[j]);
                if( compare > 0 )
                    min = j;
            }
            exchange(array, i, min);
        }
    }

    private static void exchange( int[] array, int i, int j ) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        try {
            Thread.sleep(32);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        panel.repaint();
    }

    public static void sort( int[] array ) {
        System.out.println("start sorting!");
        for( int i = 0; i < array.length; ++ i ) {
            int min = i;
            for( int j = i+1; j < array.length; ++ j ) {
                if( array[min] > array[j] )
                    min = j;
            }
            exchange(array, i, min);
        }
    }

    private static ArrayPanel panel;

    public static void main( String[] args ) {

        int[] array = new int[1024];

        Random rand = new Random();
        for( int i = 0; i < array.length; ++ i )
            array[i] = rand.nextInt(200);

        panel = ArrayPanel.startArrayGraphic(array);

        sort(array);

        System.out.println("Finish sorting!");
    }
}