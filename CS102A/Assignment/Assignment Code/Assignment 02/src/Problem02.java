public class Problem02 {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int T = sc.nextInt();
        String[][] arr = new String[T][8];
        for (int i = 0; i < T; i++){
            for (int j = 0; j < 8; j++){
                arr[i][j] = sc.next();
            }
        }
        boolean[] result = new boolean[T];
        for (int i = 0; i < T; i++){
            String[] str = new String[8];
            for (int j = 0; j < 8; j++){
                str[j] = arr[i][j];
            }
            result[i] = isSolution(changeCoordinate(str));
        }
        for (int i = 0; i < T; i++){
            if (result[i]){
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

    }

    private static boolean isSolution(int[][] chessBoard){
        for (int i = 0; i < 8; i++){
            int sum1 = 0;
            int sum2 = 0;
            for (int j = 0; j < 8; j++){
                sum1 += chessBoard[i][j];
                sum2 += chessBoard[j][i];
            }
            if (sum1 != 1 || sum2 != 1){
                return false;
            }
        }
        if (chessBoard[0][0] + chessBoard[1][1] + chessBoard[2][2] + chessBoard[3][3] + chessBoard[4][4] + chessBoard[5][5]
        + chessBoard[6][6] + chessBoard[7][7] > 1){
            return false;
        }
        if (chessBoard[0][1] + chessBoard[1][2] + chessBoard[2][3] + chessBoard[3][4] + chessBoard[4][5] + chessBoard[5][6]
                + chessBoard[6][7] > 1){
            return false;
        }
        if (chessBoard[0][2] + chessBoard[1][3] + chessBoard[2][4] + chessBoard[3][5] + chessBoard[4][6] + chessBoard[5][7] > 1){
            return false;
        }
        if (chessBoard[0][3] + chessBoard[1][4] + chessBoard[2][5] + chessBoard[3][6] + chessBoard[4][7] > 1){
            return false;
        }
        if (chessBoard[0][4] + chessBoard[1][5] + chessBoard[2][6] + chessBoard[3][7] > 1){
            return false;
        }
        if (chessBoard[0][5] + chessBoard[1][6] + chessBoard[2][7] > 1){
            return false;
        }
        if (chessBoard[0][6] + chessBoard[1][7] > 1){
            return false;
        }
        if (chessBoard[1][0] + chessBoard[2][1] + chessBoard[3][2] + chessBoard[4][3] + chessBoard[5][4] + chessBoard[6][5]
                + chessBoard[7][6] > 1){
            return false;
        }
        if (chessBoard[2][0] + chessBoard[3][1] + chessBoard[4][2] + chessBoard[5][3] + chessBoard[6][4] + chessBoard[7][5] > 1){
            return false;
        }
        if (chessBoard[3][0] + chessBoard[4][1] + chessBoard[5][2] + chessBoard[6][3] + chessBoard[7][4] > 1){
            return false;
        }
        if (chessBoard[4][0] + chessBoard[5][1] + chessBoard[6][2] + chessBoard[7][3] > 1){
            return false;
        }
        if (chessBoard[5][0] + chessBoard[6][1] + chessBoard[7][2] > 1){
            return false;
        }
        if (chessBoard[6][0] + chessBoard[7][1] > 1){
            return false;
        }
        if (chessBoard[0][7] + chessBoard[1][6] + chessBoard[2][5] + chessBoard[3][4] + chessBoard[4][3] + chessBoard[5][2]
                + chessBoard[6][1] + chessBoard[7][0] > 1){
            return false;
        }
        if (chessBoard[0][6] + chessBoard[1][5] + chessBoard[2][4] + chessBoard[3][3] + chessBoard[4][2] + chessBoard[5][1]
                + chessBoard[6][0] > 1){
            return false;
        }
        if (chessBoard[0][5] + chessBoard[1][4] + chessBoard[2][3] + chessBoard[3][2] + chessBoard[4][1] + chessBoard[5][0] > 1){
            return false;
        }
        if (chessBoard[0][4] + chessBoard[1][3] + chessBoard[2][2] + chessBoard[3][1] + chessBoard[4][0] > 1){
            return false;
        }
        if (chessBoard[0][3] + chessBoard[1][2] + chessBoard[2][1] + chessBoard[3][0] > 1){
            return false;
        }
        if (chessBoard[0][2] + chessBoard[1][1] + chessBoard[2][0] > 1){
            return false;
        }
        if (chessBoard[0][1] + chessBoard[1][0] > 1){
            return false;
        }
        if (chessBoard[1][7] + chessBoard[2][6] + chessBoard[3][5] + chessBoard[4][4] + chessBoard[5][3] + chessBoard[6][2]
                + chessBoard[7][1] > 1){
            return false;
        }
        if (chessBoard[2][7] + chessBoard[3][6] + chessBoard[4][5] + chessBoard[5][4] + chessBoard[6][3] + chessBoard[7][2] > 1){
            return false;
        }
        if (chessBoard[3][7] + chessBoard[4][6] + chessBoard[5][5] + chessBoard[6][4] + chessBoard[7][3] > 1){
            return false;
        }
        if (chessBoard[4][7] + chessBoard[5][6] + chessBoard[6][5] + chessBoard[7][4] > 1){
            return false;
        }
        if (chessBoard[5][7] + chessBoard[6][6] + chessBoard[7][5] > 1){
            return false;
        }
        if (chessBoard[6][7] + chessBoard[7][6] > 1){
            return false;
        }
        return true;
    }

    private static int[][] changeCoordinate(String[] str){
        int[][] chessBoard = new int[8][8];
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                chessBoard[i][j] = 0;
            }
        }
        for (int i = 0; i < str.length; i++){
            chessBoard[calX(str[i])][calY(str[i])] = 1;
        }
        return chessBoard;
    }

    private static int calX(String s){
        switch (s.charAt(0)){
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                return -1;
        }
    }

    private static int calY(String s){
        switch (s.charAt(1)){
            case '1':
                return 0;
            case '2':
                return 1;
            case '3':
                return 2;
            case '4':
                return 3;
            case '5':
                return 4;
            case '6':
                return 5;
            case '7':
                return 6;
            case '8':
                return 7;
            default:
                return -1;
        }
    }
}
