public class Problem01 {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print(getAnswer(sc.nextLine(),sc.nextLine(),sc.nextInt(),sc.nextInt()));
    }

    // 栅栏密码
    private static String getFence(String cipher){
        int N = Integer.parseInt(cipher.substring(cipher.length() - 1));
        String s = "";
        /*
        例如一个16位字母后面加一个5
        输出顺序为第1、6、11、16；2、7、12；3、8、13；4、9、14；5、10、15位
         */
        for (int i = 1; i <= N; i++){
            for (int j = i; j <= cipher.length() - 1; j += N){
                s += cipher.charAt(j - 1);
            }
        }
        return s;
    }

    // 凯撒密码
    private static String getCaesar(String cipher, int N){
        char[] arr = cipher.toCharArray();
        for (int i = 0; i < arr.length; i++){
            if (arr[i] >= 65 && arr[i] <= 90){
                arr[i] += N;
                if (arr[i] > 90){
                    arr[i] -= 26;
                }
            }
            if (arr[i] >= 97 && arr[i] <= 122){
                arr[i] += N;
                if (arr[i] > 122){
                    arr[i] -= 26;
                }
            }
        }
        String s = "";
        for (int i = 0; i < arr.length; i++){
            s += arr[i];
        }
        return s;
    }

    // 维吉尼亚密码
    public static String getAnswer(String fCipher, String cCipher, int cN, int M){
        String cipher = getCaesar(cCipher, cN);
        String fC = getFence(fCipher);
        String key = "";
        for (int i = 0; i < M; i++){
            key += fC.charAt(i);
        }
        int count = 1; // 计数器，计数这是第几个英文字母
        char[] arr = cipher.toCharArray();
        for (int i = 0; i < arr.length; i++){
            int keyValue = 0;
            if (key.charAt(count % M - 1 + ((count % M == 0) ? M : 0)) >= 65 && key.charAt(count % M - 1 + ((count % M == 0) ? M : 0)) <= 90){
                keyValue = key.charAt(count % M - 1 + ((count % M == 0) ? M : 0)) - 65;
            }
            if (key.charAt(count % M - 1 + ((count % M == 0) ? M : 0)) >= 97 && key.charAt(count % M - 1 + ((count % M == 0) ? M : 0)) <= 122){
                keyValue = key.charAt(count % M - 1 + ((count % M == 0) ? M : 0)) - 97;
            }
            if (arr[i] >= 65 && arr[i] <= 90){
                if (arr[i] + keyValue > 90){
                    arr[i] = (char)(arr[i] + keyValue - 26);
                    //System.out.println(arr[i]);
                } else {
                    arr[i] = (char)(arr[i] + keyValue);
                }
                count++;
            }
            if (arr[i] >= 97 && arr[i] <= 122){
                if (arr[i] + keyValue > 122){
                    arr[i] = (char)(arr[i] + keyValue - 26);
                } else {
                    arr[i] = (char)(arr[i] + keyValue);
                }
                count++;
            }

        }
        String s = "";
        for (int i = 0; i < arr.length; i++){
            s += arr[i];
        }
        return s;
    }
}
