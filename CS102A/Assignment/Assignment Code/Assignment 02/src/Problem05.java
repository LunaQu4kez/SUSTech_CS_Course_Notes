public class Problem05 {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        int r = s.nextInt();
        if (r == 0){
            System.out.println(1);
            return;
        }
        int answer = 0;
        int[] arr = new int[1600];
        for (int i = 0; i < 1600; i++){
            arr[i] = 0;
        }
        for (double d = 1; d < r; d++){
            if (2 * r % d == 0 && d % 4 == 1){
                for (int a = 1; a < Math.sqrt(r / d); a++){
                    if (isInteger(Math.sqrt(2 * r / d - a * a))){
                        if (!find((int)(d*a*Math.sqrt(2 * r / d - a * a)), arr) && !find((int)(r - a * a * d), arr)){
                            //System.out.println("is " + (r - a * a * d));
                            arr[answer] = (int) (r - a * a * d);
                            answer++;

                        }
                    }
                }
            }
        }
        System.out.println(8*answer + 4);

    }

    private static boolean isInteger(double b){
        return b - (int)b == 0;
    }

    private static boolean find(int x, int[] arr){
        boolean b = false;
        for (int i = 0; i < arr.length; i++){
            if (x == arr[i]){
                b = true;
            }
        }
        return b;
    }
}

