package exer;

public class BinaryRangeSearch {
    /**
     * Search a key in a part of the array (which is [fromIndex, toIndex-1]) using binary search. The range [fromIndex, toIndex-1] must be sorted before searching.
     * If array is not sorted on the interval [fromIndex, toIndex-1], calling rangeSearch(array, fromIndex, toIndex, key) results in undefined behavior.
     * @param array the array to be searched
     * @param fromIndex the index of the first element(inclusive) to be searched
     * @param toIndex the index of the last element(exclusive) to be searched
     * @param key the key to be searched
     * @return an array of length two: [first, last]. If key is found in the specified part of the array, then "first" is the index of the first found element, "last" is the index of the last found element.
     * If key is not found in the specified part of the array, then "first" should be -1, and "last" should be the first element in interval [fromIndex, toIndex-1] that is greater than key.
     * If all elements in the interval are less than key, return [-1, toIndex].
     */
    public static int[] rangeSearch( int[] array, int fromIndex, int toIndex, int key ) {
        // Write your code here.
    }
}
