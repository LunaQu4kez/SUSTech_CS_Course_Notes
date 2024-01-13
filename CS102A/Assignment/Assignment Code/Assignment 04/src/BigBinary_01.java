public class BigBinary_01 {

    int[] bits;
    boolean positive;

    public BigBinary_01(int[] bits, boolean positive){
        if (bits.length == 0){
            this.bits = new int[1];
        } else {
            this.bits = new int[bits.length];
            for (int i = 0; i < bits.length; i++){
                this.bits[i] = bits[bits.length - 1 - i];
            }
        }
        this.positive = positive;
    }

    public String toString(){
        int sum = 0;
        for (int i = 0; i < bits.length; i++){
            sum += bits[i];
            if (sum != 0){
                StringBuilder tempStr = new StringBuilder();
                for (int j = bits.length - 1; j >= 0; j--){
                    tempStr.append(bits[j]);
                }
                String result = (tempStr.toString()).replaceFirst("^0*","");
                return (positive ? "" : "-") + result;
            }
        }
        return "0";
    }

    public BigBinary_01 multiply(BigBinary_01 bigbinary){
        this.positive = (this.positive == bigbinary.positive);

        int[] bitsUP = to256(this.bits);
        int[] tempBitsUP = to256(bigbinary.bits);

        int[] tempBits;

        if (bitsUP.length >= tempBitsUP.length){
            tempBits = new int[bitsUP.length];
            for (int i = 0; i < tempBitsUP.length; i++){
                tempBits[i] = tempBitsUP[i];
            }
        } else {
            int[] bits2 = new int[tempBitsUP.length];
            for (int i = 0; i < bitsUP.length; i++){
                bits2[i] = bitsUP[i];
            }
            bitsUP = bits2;
            tempBits = new int[tempBitsUP.length];
            for (int i = 0; i < tempBitsUP.length; i++){
                tempBits[i] = tempBitsUP[i];
            }
        }

        int pre = 0;
        int lastPre = 0;

        int[] result = new int[2 * bitsUP.length + 1];
        for (int i = 0; i < 2 * bitsUP.length + 1; i++){
            for (int j = 0; j <= i; j++){
                try {
                    result[i] += bitsUP[j] * tempBits[i - j];
                } catch (Exception e){}
            }
            pre = 0;
            result[i] += lastPre;
            while (result[i] >= 256){
                result[i] -= 256;
                pre++;
            }
            lastPre = pre;
        }

        this.bits = to2(result);
        return this;
    }

    public BigBinary_01 add(BigBinary_01 bigbinary){
//        System.out.print("b1 ");
//        for (int i = 0; i < this.bits.length; i++){
//            System.out.print(this.bits[i]);
//        }
//        System.out.println();
//        System.out.print("b2 ");
//        for (int i = 0; i < bigbinary.bits.length; i++){
//            System.out.print(bigbinary.bits[i]);
//        }
//        System.out.println();


        int sum = 0;
        int pre = 0;

        if (this.positive == bigbinary.positive){

            if (this.bits.length < bigbinary.bits.length){
                int[] bits2 = new int[bigbinary.bits.length + 1];
                for (int i = 0; i < this.bits.length; i++){
                    bits2[i] = this.bits[i];
                }
                this.bits = bits2;
            } else {
                int[] bits2 = new int[this.bits.length + 1];
                for (int i = 0; i < this.bits.length; i++){
                    bits2[i] = this.bits[i];
                }
                this.bits = bits2;
            }

//            System.out.print("this ");
//            for (int i = 0; i < this.bits.length; i++){
//                System.out.print(this.bits[i]);
//            }
//            System.out.println();
//            System.out.print("bits ");
//            for (int i = 0; i < bigbinary.bits.length; i++){
//                System.out.print(bigbinary.bits[i]);
//            }
//            System.out.println();

            for (int i = 0; i < this.bits.length - 1; i++){
                try {
                    sum = this.bits[i] + bigbinary.bits[i] + pre;
                    if (sum >= 2){
                        pre = 1;
                        this.bits[i] = sum - 2;
                    } else {
                        pre = 0;
                        this.bits[i] = sum;
                    }
                } catch (Exception e){
                    sum = this.bits[i] + pre;
                    if (sum >= 2){
                        pre = 1;
                        this.bits[i] = sum - 2;
                    } else {
                        pre = 0;
                        this.bits[i] = sum;
                    }
                }
            }
            if (pre == 1){
                this.bits[this.bits.length - 1] = 1;
            }

            return this;

        } else {
            boolean isThisBigger = true;
            int[] tempBits;

            if (this.bits.length < bigbinary.bits.length){
                int[] bits2 = new int[bigbinary.bits.length];
                for (int i = 0; i < this.bits.length; i++){
                    bits2[i] = this.bits[i];
                }
                this.bits = bits2;
                tempBits = new int[bigbinary.bits.length];
                for (int i = 0; i < bigbinary.bits.length; i++){
                    tempBits[i] = bigbinary.bits[i];
                }
            } else if (this.bits.length > bigbinary.bits.length){
                tempBits = new int[this.bits.length];
                for (int i = 0; i < bigbinary.bits.length; i++){
                    tempBits[i] = bigbinary.bits[i];
                }
            } else {
                tempBits = new int[bigbinary.bits.length];
                for (int i = 0; i < bigbinary.bits.length; i++){
                    tempBits[i] = bigbinary.bits[i];
                }
            }

//            System.out.print("this ");
//            for (int i = 0; i < this.bits.length; i++){
//                System.out.print(this.bits[i]);
//            }
//            System.out.println();
//            System.out.print("bits ");
//            for (int i = 0; i < tempBits.length; i++){
//                System.out.print(tempBits[i]);
//            }
//            System.out.println();

            for (int i = this.bits.length - 1; i >= 0; i--){
                if (this.bits[i] - tempBits[i] < 0){
                    isThisBigger = false;
                    break;
                } else if (this.bits[i] - tempBits[i] > 0){
                    break;
                }
            }


            if (this.positive && isThisBigger){
                this.positive = true;

                this.bits = bitsMinus(this.bits, tempBits);

            } else if (this.positive && !isThisBigger){
                this.positive = false;
                this.bits = bitsMinus(tempBits, this.bits);
            } else if (!this.positive && isThisBigger){
                this.positive = false;
                this.bits = bitsMinus(this.bits, tempBits);
            } else {
                this.positive = true;
                this.bits = bitsMinus(tempBits, this.bits);
            }

//            System.out.print("b1 ");
//            for (int i = 0; i < this.bits.length; i++){
//                System.out.print(this.bits[i]);
//            }
//            System.out.println();
//            System.out.print("b2 ");
//            for (int i = 0; i < bigbinary.bits.length; i++){
//                System.out.print(bigbinary.bits[i]);
//            }
//            System.out.println();

            return this;
        }
    }

    public static BigBinary_01 add(BigBinary_01 b1, BigBinary_01 b2){
        int[] bits = new int[b1.bits.length];
        for (int i = 0; i < bits.length; i++){
            bits[i] = b1.bits[bits.length - 1 - i];
        }
        boolean positive = b1.positive;
        BigBinary_01 b1_1 = new BigBinary_01(bits, positive);
        BigBinary_01 result = b1_1.add(b2);
        return result;
    }

    public static BigBinary_01 minus(BigBinary_01 b1, BigBinary_01 b2){
        int[] bits = new int[b1.bits.length];
        for (int i = 0; i < bits.length; i++){
            bits[i] = b1.bits[bits.length - 1 - i];
        }
        boolean positive = b1.positive;
        BigBinary_01 b1_1 = new BigBinary_01(bits, positive);
        return b1_1.minus(b2);
    }

    public BigBinary_01 minus(BigBinary_01 bigbinary){
        int[] bits = new int[bigbinary.bits.length];
        for (int i = 0; i < bits.length; i++){
            bits[i] = bigbinary.bits[bits.length - 1 - i];
        }
        boolean positive = !bigbinary.positive;
        return add(new BigBinary_01(bits, positive));
    }

    public static BigBinary_01 multiply(BigBinary_01 b1, BigBinary_01 b2){
        int[] bits = new int[b1.bits.length];
        for (int i = 0; i < bits.length; i++){
            bits[i] = b1.bits[bits.length - 1 - i];
        }
        boolean positive = b1.positive;
        BigBinary_01 b1_1 = new BigBinary_01(bits, positive);
        BigBinary_01 result = b1_1.multiply(b2);
        return result;
    }

    public static int[] bitsMinus(int[] bits1, int[] bits2){
        int sum = 0;
        int pre = 0;
        for (int i = 0; i < bits1.length; i++){
            sum = bits1[i] - bits2[i] + pre;
            if (sum < 0){
                pre = -1;
                bits1[i] = sum + 2;
            } else {
                pre = 0;
                bits1[i] = sum;
            }
        }
        return bits1;
    }

    public static int[] to256(int[] bits){
        int[][] form = new int[bits.length / 8 + 1][8];
        for (int i = 0; i < bits.length / 8 + 1; i++){
            for (int j = 0; j < 8; j++){
                try {
                    form[i][j] = bits[8 * i + j];
                } catch (Exception e){
                    form[i][j] = 0;
                }
            }
        }

        int[] result = new int[bits.length / 8 + 1];
        for (int k = 0; k < result.length; k++){
            result[k] = form[k][0] + 2*form[k][1] + 4*form[k][2] + 8*form[k][3] + 16*form[k][4] + 32*form[k][5]
                    + 64*form[k][6] + 128*form[k][7];
        }
        return result;
    }

    public static int[] to2(int[] temp){
        int[][] form = new int[temp.length][8];
        for (int i = 0; i < temp.length; i++){
            int sum = temp[i];
            form[i][7] = sum / 128;
            sum -= form[i][7]*128;
            form[i][6] = sum / 64;
            sum -= form[i][6]*64;
            form[i][5] = sum / 32;
            sum -= form[i][5]*32;
            form[i][4] = sum / 16;
            sum -= form[i][4]*16;
            form[i][3] = sum / 8;
            sum -= form[i][3]*8;
            form[i][2] = sum / 4;
            sum -= form[i][2]*4;
            form[i][1] = sum / 2;
            sum -= form[i][1]*2;
            form[i][0] = sum;
        }

        int[] result = new int[temp.length * 8];
        for (int i = 0; i < result.length; i++){
            result[i] = form[i / 8][i % 8];
        }
        return result;
    }
}