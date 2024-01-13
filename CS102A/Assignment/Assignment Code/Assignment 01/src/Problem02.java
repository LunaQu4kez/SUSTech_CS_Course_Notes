public class Problem02 {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        String id;
        boolean b1;
        try {
            int i = s.nextInt();
            id = String.valueOf(i); // 将int类型id转换成String类型
            b1 = !(id.length() != 8 || id.startsWith("0")); // 判断id是否合理
        }catch(Exception e){
            b1 = false;
            String m1 = s.next();
        }
        String status = s.next();
        char[] chars = status.toCharArray(); // 将字符串status转化成char数组
        int y = 0; // 计数Y的个数
        int n = 0; // 计数N的个数
        for (int i = 0; i < chars.length; i++) {  // 遍历数组，计数
            if (chars[i] == 'Y')
                y++;
            else if (chars[i] == 'N')
                n++;
        }
        boolean b2 = (y + n == 7 && status.length() == 7); // 判断status是否合法
        boolean b3 = (y >= 4 && !status.contains("NNN")); // 判断是否按要求完成PCR检测
        if (b1 && b2 && b3)
            System.out.println("Good, keep it up!");
        else if (b1 && b2)
            System.out.println("Has not participated in Nucleic Acid PCR tests as required!");
        else
            System.out.println("Not valid");
    }
}

