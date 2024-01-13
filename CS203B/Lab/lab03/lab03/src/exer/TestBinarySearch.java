package exer;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class TestBinarySearch {
    private static int[] linearSearch( int[] array, int fromIndex, int toIndex, int key ) {
        int left;
        for( left = fromIndex; left < toIndex; left ++ ) {
            if( array[left] >= key )
                break;
        }

        if( left >= toIndex )
            return new int[] {-1, toIndex};
        if( array[left] > key )
            return new int[] {-1, left};

        for( int right = left; right < toIndex-1; right ++ ) {
            if( array[right+1] > key)
                return new int[] {left, right};
        }
        return new int[] {left, toIndex-1};
    }

    private static void testOneSample(int[] testArray, int left, int right, int key) {
        int[] res1 = linearSearch(testArray, left, right, key);

        Stopwatch watch = new Stopwatch();
        int[] res2 = BinaryRangeSearch.rangeSearch(testArray, left, right, key);
        double time = watch.elapsedTime();

        System.out.printf("Result is %s, time spent is %f\n",
                (res1[0] == res2[0] && res1[1] == res2[1]) ? "Right" : "Wrong", time);
    }

    private static void testArray(int[] testArray, int min, int max) {
        int size = testArray.length;
        for (int i = 0; i < 20; ++i) {
            int a = StdRandom.uniform(0, size);
            int b = StdRandom.uniform(0, size);
            int key = StdRandom.uniform(min, max);

            testOneSample(testArray, Math.min(a, b), Math.max(a,b), key);
        }
        testOneSample(testArray, 0, testArray.length, 0);
    }

    public static void main(String[] args) {
        final int SIZE = 200000;

        int[] testArray1 = new int[SIZE];
        for (int i = 0; i < testArray1.length; ++i)
            testArray1[i] = StdRandom.uniform(-100, 100);
        Arrays.sort(testArray1);
        testArray(testArray1, -100, 100);

        int[] testArray2 = new int[SIZE];
        for (int i = 0; i < testArray2.length; ++i)
            testArray1[i] = StdRandom.uniform(-3, 3);
        Arrays.sort(testArray2);
        testArray(testArray2, -3, 3);
    }
}
