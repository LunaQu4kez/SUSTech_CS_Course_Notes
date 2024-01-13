public class Problem04 {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        int n = s.nextInt();
        if (n % 2 == 0){
            isEven(n);
        } else {
            isOdd(n);
        }
    }

    private static void isEven(int n){
        System.out.println(9 * n + 11);
        for (int i = 0; i < n/2; i++){
            System.out.println(4*i + " " + 2);
            System.out.println(4*i + 1 + " " + 2);
            System.out.println(4*i + 2 + " " + 2);
            System.out.println(4*i + " " + 1);
            System.out.println(4*i + 2 + " " + 1);
            System.out.println(4*i + " " + -2);
            System.out.println(4*i + 1 + " " + -2);
            System.out.println(4*i + 2 + " " + -2);
            System.out.println(4*i + " " + -1);
            System.out.println(4*i + 2 + " " + -1);
        }
        for (int i = -2; i <= 2*n; i++){
            if (i != 0) {
                System.out.println(i + " " + 0);
            }
            System.out.println(i + " " + -4);
        }
        for (int i = -3; i <= -1; i++){
            System.out.println(-2 + " " + i);
            System.out.println(2*n + " " + i);
        }
    }

    private static void isOdd(int n){
        System.out.println(9 * n + 9);
        for (int i = 1; i <= n/2; i++){
            System.out.println(4*i-2 + " " + 2);
            System.out.println(4*i-1 + " " + 2);
            System.out.println(4*i + " " + 2);
            System.out.println(4*i-2 + " " + -2);
            System.out.println(4*i-1 + " " + -2);
            System.out.println(4*i + " " + -2);
        }
        for (int i = 1; i <= n/2; i++){
            System.out.println(4*i-2 + " " + 1);
            System.out.println(4*i + " " + 1);
            System.out.println(4*i-2 + " " + -1);
            System.out.println(4*i + " " + -1);
        }
        for (int i = -2; i <= 2*n; i++){
            if (i != 0){
                System.out.println(i + " " + 0);
            }
        }
        for (int i = 1; i <= 3; i++){
            System.out.println(0 + " " + i);
            System.out.println(2*n + " " + i);
        }
        for (int i = 0; i <= 2*n; i++){
            System.out.println(i + " " + 4);
        }
        System.out.println("-2 -1");
        System.out.println("0 -1");
        System.out.println("-2 -2");
        System.out.println("-1 -2");
        System.out.println("0 -2");
    }
}
