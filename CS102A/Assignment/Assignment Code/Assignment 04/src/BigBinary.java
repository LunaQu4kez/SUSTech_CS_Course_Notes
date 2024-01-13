public class BigBinary {
    private int[]bits;
    private boolean positive;
    public BigBinary(int[] bits, boolean positive){
        this.bits = new int[bits.length];
        for (int i = 0; i < bits.length; i++) {
            this.bits[i] = bits[bits.length - 1 - i];
            //System.out.println(this.bits[i]);
        }
        this.positive = positive;
    }
    public String toString(){
        int begin = 0;
        StringBuilder str = new StringBuilder();
        if (bits.length==0){
            return "0";
        }
        if (!positive) {
            str.append("-");
        }
        for (int i = this.bits.length-1; i>=0; i--){
            if (bits[i] != 0){
                begin = i;
            }
            if (i<=begin){
                str.append(bits[i]);
            }
        }
        if (str.toString().equals("-0")){
            return "0";
        }
        return str.toString();
    }
    public static int[] realAdd (BigBinary b1,BigBinary b2) {
        int[] result = new int[Math.max(b1.bits.length, b2.bits.length) + 1];
        for (int i = 0; i < result.length-1; i++) {
            int r = 0;
            try {
                r = b1.bits[i] + b2.bits[i];
            } catch(Exception e){
                try {
                    r = b1.bits[i];
                }catch (Exception d){
                    r = b2.bits[i];
                }
            }
            result[i] += r;
            if (result[i]>=2){
                result[i] -= 2;
                result[i+1] += 1;
            }
        }
        return result;
    }
    public static int[] compare (BigBinary b1, BigBinary b2) {
        int[] b3 = new int[b1.bits.length > b2.bits.length ? b1.bits.length : b2.bits.length];
        if (b1.bits.length > b2.bits.length) {
            for (int i = 0; i < b2.bits.length; i++) {
                b3[i] = b2.bits[i];
            }
            for (int j = b3.length - 1; j >= 0; j--) {
                if (b3[j] != b1.bits[j]) {
                    return b3[j] > b1.bits[j] ? b2.bits : b1.bits;
                }
            }
            int[] b4 = {0};
            return b4;
        } else {
            for (int i = 0; i < b1.bits.length; i++) {
                b3[i] = b1.bits[i];
            }
            for (int j = b3.length - 1; j >= 0; j--) {
                if (b3[j] != b2.bits[j]) {
                    return b3[j] > b2.bits[j] ? b1.bits : b2.bits;
                }
            }
            int[] b4 = {0};
            return b4;
        }
    }
    public static int[] realMinus (BigBinary b1, BigBinary b2){
        if (b1.bits == compare(b1,b2)){
            int[] result = new int[Math.max(b1.bits.length, b2.bits.length) + 1];
            for (int i = 0; i < result.length-1; i++) {
                int r = 0;
                try {
                    r = b1.bits[i] - b2.bits[i];
                } catch(Exception e){
                    try {
                        r = b1.bits[i];
                    }catch (Exception d){
                        r = 0-b2.bits[i];
                    }
                }
                result[i]+=r;
                if (result[i] < 0){
                    result[i] += 2;
                    result[i+1] -=1;
                }
            }
            return result;
        }else {
            int[] result = new int[Math.max(b1.bits.length, b2.bits.length) + 1];
            for (int i = 0; i < result.length-1; i++) {
                int r = 0;
                try {
                    r = b2.bits[i] - b1.bits[i];
                } catch(Exception e){
                    try {
                        r = b2.bits[i];
                    }catch (Exception d){
                        r = 0-b1.bits[i];
                    }
                }
                result[i] += r;
                if (result[i] <0) {
                    result[i] += 2;
                    result[i + 1] -= 1;
                }
            }
            return result;
        }
    }
    public BigBinary add(BigBinary bigbinary) {
        if (this.positive == bigbinary.positive) {
            this.bits = realAdd(this,bigbinary);
            return this;
        }else {
            if (this.bits == compare(this,bigbinary)){
                this.bits = realMinus(this,bigbinary);
                return this;
            }else {
                this.bits = realMinus(this,bigbinary);
                this.positive = bigbinary.positive;
                return this;
            }
        }
    }
    public BigBinary minus(BigBinary bigbinary){
        if (this.positive!=bigbinary.positive){
            this.bits = realAdd(this,bigbinary);
            return this;
        }else {
            if (this.bits == compare(this,bigbinary)){
                this.bits = realMinus(this,bigbinary);
                return this;
            }else {
                this.bits = realMinus(this,bigbinary);
                this.positive = !bigbinary.positive;
                return this;
            }
        }
    }

    public static BigBinary add(BigBinary b1, BigBinary b2){
        if (b1.positive == b2.positive) {
            int[] arr = realAdd(b1,b2);
            int[] m = new int[arr.length];
            for (int i = 0; i< m.length; i++){
                m[i] = arr[m.length-i-1];
            }
            return new BigBinary(m, b1.positive);
        }else {
            if (b1.bits == compare(b1,b2)){
                int[] arr = realMinus(b1,b2);
                int[] m = new int[arr.length];
                for (int i = 0; i< m.length; i++){
                    m[i] = arr[m.length-i-1];
                }
                return new BigBinary(m, b1.positive);
            }else {
                int[] arr = realMinus(b1,b2);
                int[] m = new int[arr.length];
                for (int i = 0; i< m.length; i++){
                    m[i] = arr[m.length-i-1];
                }
                return new BigBinary(m, b2.positive);
            }
        }
    }
    public static BigBinary minus(BigBinary b1, BigBinary b2){
        if (b1.positive!=b2.positive){
            int[] arr = realAdd(b1,b2);
            int[] m = new int[arr.length];
            for (int i = 0; i< m.length; i++){
                m[i] = arr[m.length-i-1];
            }
            return new BigBinary(m, b1.positive);
        }else {
            if (b1.bits == compare(b1,b2)){
                int[] arr = realMinus(b1,b2);
                int[] m = new int[arr.length];
                for (int i = 0; i< m.length; i++){
                    m[i] = arr[m.length-i-1];
                }
                return new BigBinary(m, b1.positive);
            }else {
                int[] arr = realMinus(b1,b2);
                int[] m = new int[arr.length];
                for (int i = 0; i< m.length; i++){
                    m[i] = arr[m.length-i-1];
                }
                return new BigBinary(m, !b2.positive);
            }
        }
    }
    public static BigBinary multiply(BigBinary b1, BigBinary b2){

        int[] bitsUP = biToSix(b1.bits);
        int[] tempBitsUP = biToSix(b2.bits);

        int[] resultArr = new int[bitsUP.length + tempBitsUP.length];
        int pre = 0;
        int last = 0;
        for (int i = 0; i < resultArr.length; i++) {
            int add = 0;
            for (int j = 0; j <= i; j++) {
                try {
                    add += bitsUP[j] * tempBitsUP[i - j];
                } catch (Exception e){
                    add += 0;
                }
            }
            pre = 0;
            resultArr[i] = add + last;
            while (true){
                if (resultArr[i] < 2){
                    break;
                }
                resultArr[i] -= 2;
                pre += 1;
            }
            last = pre;
        }

        int[] fine = sixToBi(resultArr);
        int[] m = new int[fine.length];
        for (int i = 0; i< m.length; i++){
            m[i] = fine[m.length-i-1];
        }
        boolean positive = (b1.positive == b2.positive);
        return new BigBinary(m, positive);
    }
    public BigBinary multiply(BigBinary bigbinary){
        this.positive = (this.positive == bigbinary.positive);

        int[] bitsUP = biToSix(this.bits);
        int[] tempBitsUP = biToSix(bigbinary.bits);

        int[] resultArr = new int[bitsUP.length + tempBitsUP.length];
        int pre = 0;
        int last = 0;
        for (int i = 0; i < resultArr.length; i++) {
            int add = 0;
            for (int j = 0; j <= i; j++) {
                try {
                    add += bitsUP[j] * tempBitsUP[i - j];
                } catch (Exception e){
                    add += 0;
                }
            }
            pre = 0;
            resultArr[i] = add + last;
            while (true){
                if (resultArr[i] < 2){
                    break;
                }
                resultArr[i] -= 2;
                pre += 1;
            }
            last = pre;

        }
        int[] m = new int[resultArr.length];
        for (int i = 0; i< m.length; i++){
            m[i] = resultArr[m.length-i-1];
        }

        this.bits = sixToBi(resultArr);
        return this;
    }
    public static int[] biToSix(int[] bi){
        int [][] bii = new int[bi.length/6+1][6];
        int [] six = new int[bi.length/6+1];
        for (int i = 0; i<bi.length/6; i++){
            for (int j = 0; j<6; j++){
                try {
                    bii[i][j] = bi[i+j];
                }catch (Exception e){
                    bii[i][j] = 0;
                }
            }
        }
        for (int i = 0; i<bi.length/6;i++){
            for (int j = 0;j<6; j++){
                six[i] = six[i] + bii[i][j];
            }
        }
        return six;
    }
    public static int[] sixToBi(int[] six){
        int[][] bii = new int[six.length][6];
        for (int i = 0; i<six.length; i++){
            for (int j = 0; j<6; j++){
                bii[i][j] = (int) ((int)six[i]%(Math.pow(2,j+1)));
            }
        }
        int[] bi = new int[6*six.length];
        for (int i = 0; i<six.length;i++){
            for (int j = 0; j<6; j++){
                bi[i+j] = bii[i][j];
            }
        }
        return bi;
    }
}



