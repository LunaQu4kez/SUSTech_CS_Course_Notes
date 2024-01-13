import java.util.Scanner;

public class Cards {
    /**
     * Using the order the cards are destroyed, calculate the initial order of the cards.
     * @param destroyOrder the order the cards are destroyed.
     * @return the initial order
     */
    public static int[] calculateInitCards( int[] destroyOrder ) {
        // write your code here.
    }

    public static void main( String[] args ) {
        try( Scanner input = new Scanner(System.in) ) {
            int N = input.nextInt();
            int[] destroyOrder = new int[N];
            for( int i = 0; i < N; i ++ )
                destroyOrder[i] = input.nextInt();
            int[] initOrder = calculateInitCards(destroyOrder);
            for( int i : initOrder )
                System.out.println(i);
        }
    }
}