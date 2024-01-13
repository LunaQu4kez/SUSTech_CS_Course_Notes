public class Problem03 {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        /*String str1 = s.nextLine();
        if (str1.contains("  ")){
            System.out.println("Not valid");
            return;
        }
        String[] strArr1 = str1.split(" ");
        if (strArr1.length != 3){
            System.out.println("Not valid");
            return;
        }
        if (strArr1[0].length() != 2 || strArr1[1].length() != 2 || strArr1[2].length() != 2){
            System.out.println("Not valid");
            return;
        }
        int hour1 = Integer.parseInt(strArr1[0]);
        int min1 = Integer.parseInt(strArr1[1]);
        int sec1 = Integer.parseInt(strArr1[2]);
        //System.out.print(""); // 换行输入
        String str2 = s.nextLine();
        if (str2.contains("  ")){
            System.out.println("Not valid");
            return;
        }
        String[] strArr2 = str2.split(" ");
        if (strArr2.length != 3){
            System.out.println("Not valid");
            return;
        }
        if (strArr2[0].length() != 2 || strArr2[1].length() != 2 || strArr2[2].length() != 2){
            System.out.println("Not valid");
            return;
        }
        int hour2 = Integer.parseInt(strArr2[0]);
        int min2 = Integer.parseInt(strArr2[1]);
        int sec2 = Integer.parseInt(strArr2[2]);
        */
        try {
            int hour1 = s.nextInt();
            int min1 = s.nextInt();
            int sec1 = s.nextInt();
            int hour2 = s.nextInt();
            int min2 = s.nextInt();
            int sec2 = s.nextInt();

        int deltaSec = sec2 - sec1 + (min2 - min1) * 60 + (hour2 - hour1) * 3600; // 计算时间差秒数
        if (judge01(hour1, min1, sec1) && judge01(hour2, min2, sec2) && deltaSec >= 0){
            int sec = deltaSec % 60; // 秒位
            int min = ((deltaSec - sec) / 60) % 60; // 分位
            int hour = ((deltaSec - sec) / 60 - min) / 60; // 时位
            if (hour == 0 && min == 0){
                System.out.println(sec + "s");
            } else if (hour == 0 && min != 0 && sec == 0){
                System.out.println(min + "m");
            } else if (hour != 0 && min == 0 && sec == 0){
                System.out.println(hour + "h");
            } else if (hour == 0 && min != 0 && sec != 0){
                System.out.println(min + "m" + sec + "s");
            } else if (hour != 0 && min == 0 && sec != 0){
                System.out.println(hour + "h" + sec + "s");
            } else if (hour != 0 && min != 0 && sec == 0){
                System.out.println(hour + "h" + min + "m");
            } else {
                System.out.println(hour + "h" + min + "m" + sec + "s");
            }
        } else {
            System.out.println("Not valid");
        }
        }catch (Exception e){
            System.out.println("Not valid");
            return;
        }
    }

    // 判断时间是否合法的方法
    public static boolean judge01(int hour, int min, int sec){
            return (hour >= 0 && hour < 24 && min >= 0 && min < 60 && sec >= 0 && sec < 60);
    }
}