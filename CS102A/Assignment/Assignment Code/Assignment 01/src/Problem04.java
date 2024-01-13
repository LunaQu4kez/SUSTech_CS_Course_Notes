public class Problem04 {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);

        String str = s.nextLine();
        String[] strArr = str.split(" ");
        if (strArr.length != 4){
            System.out.println("Not valid");
            return;
        }
        String s1 = strArr[0];
        String s2 = strArr[1];
        String s3 = strArr[2];
        String s4 = strArr[3];

        if (!judge(s1) || !judge(s2) || !judge(s3) || !judge(s4)){
            System.out.println("Not valid");
            return;
        }
        int temp = 0; // 符合要求的week数进行计数
        if (judge2(s1)) temp = temp + 1;
        if (judge2(s2)) temp = temp + 1;
        if (judge2(s3)) temp = temp + 1;
        if (judge2(s4)) temp = temp + 1;
        if (temp == 4)
            System.out.println("100");
        else if (temp == 3)
            System.out.println("90");
        else if (temp == 2)
            System.out.println("75");
        else if (temp == 1)
            System.out.println("55");
        else
            System.out.println("30");
    }

    public static boolean judge(String status){
        char[] chars = status.toCharArray();
        int y = 0;
        int n = 0;
        for(int i = 0; i < chars.length; i++){
            if (chars[i] == 'Y')
                y = y + 1;
            else if (chars[i] == 'N')
                n = n + 1;
        }
        return (y + n == 7 && chars.length == 7);
    }

    public static boolean judge2(String status){
        char[] chars = status.toCharArray();
        int y = 0;
        int n = 0;
        for(int i = 0; i < 7; i++){
            if (chars[i] == 'Y')
                y = y + 1;
            else if (chars[i] == 'N')
                n = n + 1;
        }
        return y >= 4 && !status.contains("NNN");
    }
}
