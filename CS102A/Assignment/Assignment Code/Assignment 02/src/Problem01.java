public class Problem01 {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        int n = s.nextInt();
        double[] a = new double[n]; // a0到a(n-1)的数组
        for (int i = 0; i < n; i++){
            a[i] = s.nextDouble();
        }
        double leftBound = s.nextDouble();
        double rightBound = s.nextDouble();
        System.out.println(calValue(a, rightBound) - calValue(a, leftBound));
    }

    // 计算某一边界
    private static double calValue(double[] arr, double x){
        double sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i] / (i + 1) * calPower(x, i + 1);
        }
        return sum;
    }

    // 计算x的k次方
    private static double calPower(double x, int k){
        double product = 1;
        for (int i = 0; i < k; i++){
            product *= x;
        }
        return product;
    }
}