public class TestBB {
    public static void main(String[] args) {
        int[] b1 = {1,0,1,1,0,1};
        int[] b2 = {1,1,0};

        BigBinary bigBinary1 = new BigBinary(b1, true);
        BigBinary bigBinary2 = new BigBinary(b2, false);

        System.out.println(BigBinary.multiply(bigBinary1,bigBinary2));
        System.out.println(bigBinary1.multiply(bigBinary2));

    }
}
