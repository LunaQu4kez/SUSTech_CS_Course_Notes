public class BinaryRangeSearch {

    public static int[] binarySearch( int[] array, int target ) {
        return binarySearch(array, target, 0, array.length-1);
    }

    public static int[] binarySearch( int[] array, int target, int low, int high ) {
        int left = -1;
        int right = -1;

        int lo = low;
        int hi = high;

        while( lo <= hi ) {
            int mid = lo + (hi-lo)/2;
            if( array[mid] < target ) {
                lo = mid+1;
            } else if( array[mid] > target ) {
                hi = mid-1;
            } else if( mid == lo || array[mid-1] < target ) {
                left = mid;
                break;
            } else {
                hi = mid-1;
            }
        }
        if( left == -1 )
            return new int[]{-1,-1};
        lo = left;
        hi = high;
        while( lo <= hi ) {
            int mid = lo + (hi-lo)/2;
            if( array[mid] > target ) {
                hi = mid-1;
            } else if( mid == hi || array[mid+1] > target ) {
                right = mid;
                break;
            } else {
                lo = mid+1;
            }
        }
        return new int[]{left, right};
    }

    public static void main(String[] args ) {
        int[] array = new int[]{ -1, -1, 2, 2, 3, 3};
        int[] res = binarySearch(array, -1);
        System.out.println(res[0]+" "+res[1]);
        res = binarySearch(array, 2);
        System.out.println(res[0]+" "+res[1]);
        res = binarySearch(array, 3);
        System.out.println(res[0]+" "+res[1]);
        res = binarySearch(array, 4);
        System.out.println(res[0]+" "+res[1]);

        System.out.println();

        array = new int[]{ -1, 2, 3};
        res = binarySearch(array, -1);
        System.out.println(res[0]+" "+res[1]);
        res = binarySearch(array, 2);
        System.out.println(res[0]+" "+res[1]);
        res = binarySearch(array, 3);
        System.out.println(res[0]+" "+res[1]);
        res = binarySearch(array, 4);
        System.out.println(res[0]+" "+res[1]);
    }
}
