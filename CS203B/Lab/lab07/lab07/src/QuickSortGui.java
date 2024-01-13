import java.util.Random;

public class QuickSortGui {

    private static void update() {
        try {
            Thread.sleep(16);
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

    public static void sort( int[] array, int left, int right, Random rand ) {
        if( right - left < 8 ) {
            insertion(array, left, right);
            return;
        }

        //select pivot
        int pivot = array[rand.nextInt(right-left+1)+left];

        //do partition
        int i1 = left-1;
        int i2 = left-1;
        int i3 = right+1;
        while( i3-i2 > 1 ) {
            int value = array[i2+1];
            if( value < pivot ) {
                exchange(array, i1+1, i2+1);
                i1++;
                i2++;
            } else if( value > pivot ) {
                exchange(array, i2+1, i3-1);
                i3--;
            } else
                i2++;
        }

        sort( array, left, i1, rand );
        sort( array, i3, right, rand );
    }

    public static void sort( int[] array ) {
        Random rand = new Random();
        sort(array, 0, array.length-1, rand);
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