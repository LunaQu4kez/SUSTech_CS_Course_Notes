import java.util.Random;

public class MergeSortGraphic {

    private static void update() {
        try {
            Thread.sleep(32);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        panel.repaint();
    }

    private static void insertion( int[] array, int low, int high ) {
        for( int i = low+1; i <= high; ++ i ) {
            int tmp = array[i];
            int j;
            for( j = i; j > low && array[j-1] > tmp; -- j ) {
                array[j] = array[j-1];
                update();
            }
            array[j] = tmp;
        }
        update();
    }
    private static void merge( int[] array, int low, int mid, int high, int [] aux ) {
        System.arraycopy(array, low, aux, low, high-low+1);
        int i, j1, j2;
        for( i = low, j1=low, j2 = mid+1; j1<=mid && j2 <= high; ) {
            if( aux[j1] <= aux[j2] )
                array[i++] = aux[j1++];
            else
                array[i++] = aux[j2++];
            update();
        }
        while( j1 <= mid ) {
            array[i++] = aux[j1++];
            update();
        }
        while( j2 <= high ) {
            array[i++] = aux[j2++];
            update();
        }
    }
    /*
     *         
     */
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

    public static void sort( int[] array, int[] aux ) {
        sort(array, 0, array.length-1, aux);
    }

    private static ArrayPanel panel;

    public static void main( String[] args ) {

        int[] array = new int[1024];
        int[] aux = new int[array.length];

        Random rand = new Random();
        for( int i = 0; i < array.length; ++ i )
            array[i] = rand.nextInt(200);

        panel = ArrayPanel.startArrayGraphic(aux, array);
        update();

        sort(array, aux);

        System.out.println("Finish sorting!");
    }
}