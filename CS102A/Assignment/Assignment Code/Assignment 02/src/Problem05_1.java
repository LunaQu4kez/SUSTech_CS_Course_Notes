public class Problem05_1 {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        int r = s.nextInt();
        int ans = 1;
        if (r == 0){
            System.out.println(ans);
            return;
        }
        for (int i = 5; i <= r; i += 4){
            if (r % i == 0){
                int cnt = 0;
                do {
                    r /= i;
                    cnt++;
                } while (r % i == 0);
                if (isZhiShu(i)){
                    ans *= (2 * cnt + 1);
                }
            }
        }
        System.out.println(4 * ans);
    }

    // 判断奇数x是不是质数
    private static boolean isZhiShu(int x){
        for (int i = 3; i * i <= x; i += 2){
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }
}
