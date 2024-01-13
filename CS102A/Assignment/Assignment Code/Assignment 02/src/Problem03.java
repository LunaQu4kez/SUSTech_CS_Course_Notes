public class Problem03 {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        int n = s.nextInt();
        int[] home = new int[n];
        int[] seat = new int[n];
        int[] time = new int[n];
        for (int i = 0; i < n; i++){
            home[i] = s.nextInt();
        }
        for (int i = 0; i < n; i++){
            seat[i] = s.nextInt();
        }
        int[] home2 = bubbleSort(home);
        int[] seat2 = bubbleSort(seat);
        for (int i = 0; i < n; i++){
            time[i] = Math.abs(home2[i] - seat2[i]);
        }
        int[] time2 = bubbleSort(time);
        System.out.println(time2[n - 1]);

    }

    private static int[] bubbleSort(int[] arr) {
        for(int i = 0 ; i < arr.length - 1 ; i++) {
            for(int j = 0 ; j < arr.length - 1 - i ; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
}
