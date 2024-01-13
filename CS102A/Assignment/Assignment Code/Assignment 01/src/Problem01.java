public class Problem01 {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        String id,result;
        double tem;
        boolean b1,b2,b3;

        id = s.next();
        b1 = !(id.length() != 8 || id.startsWith("0")); // 判断id是否合理

        try {
            tem = s.nextDouble();
            b2 = (tem > 35.0 && tem < 45.0); // 判断温度是否合理
        }catch (Exception e){
            b2 = false;
            String m2 = s.next();
        }
        result = s.next();
        b3 = (result.equals("Y") || result.equals("N")); // 判断结果是否合理
        String print;
        if (b1 && b2 && b3)      // 判断是哪种情况
            print = "Submit successfully";
        else if (!b1 && b2 && b3)
            print = "Error in Student ID";
        else if (b1 && !b2 && b3)
            print = "Error in Temperature";
        else if (b1 && b2)
            print = "Error in Nucleic Acid PCR test";
        else if (!b1 && !b2 && b3)
            print = "Error in Student ID and Temperature";
        else if (!b1 && b2)
            print = "Error in Student ID and Nucleic Acid PCR test";
        else if (b1)
            print = "Error in Temperature and Nucleic Acid PCR test";
        else
            print = "Error in Student ID and Temperature and Nucleic Acid PCR test";
        System.out.println(print); // 根据判断结果输出
    }
}




