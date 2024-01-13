package exercise1;

public final class DeckQueue {

    private int[] array;

    private int top;
    private int bottom;
    private int size;

    public DeckQueue( int[] array ) {
        if( array.length < 2 )
            throw new IllegalArgumentException("array size should >= 2");
        this.array = array.clone();
        top = 0;
        bottom = array.length-1;
        size = array.length;
    }

    public int size() {
        return size;
    }

    public int first() {
        return array[top];
    }

    public int second() {
        return array[(top+1)%array.length];
    }

    public void exchangeTopTwo() {
        int second = (top+1)%array.length;
        int tmp = array[top];
        array[top] = array[second];
        array[second] = tmp;
    }

    public void moveTop2Bottom() {
        top = (top+1)%array.length;
        bottom = (bottom+1)%array.length;
    }

    public boolean isSorted() {
        int i1 = top;
        int i2 = (top+1)%array.length;

        while( i1 != bottom ) {
            if( array[i1] > array[i2] )
                return false;
            i1 = i2;
            i2 = (i2+1)%array.length;
        }
        return true;
    }

    public static void main( String[] args ) {
        DeckQueue deck = new DeckQueue(new int[] { 6, 2, 1, 3, 4, 5 });
        System.out.println(deck.first()+" "+deck.second());

        deck.moveTop2Bottom();
        System.out.println(deck.first()+" "+deck.second());

        deck.exchangeTopTwo();
        System.out.println(deck.first()+" "+deck.second());

        System.out.println(deck.isSorted());
    }
}