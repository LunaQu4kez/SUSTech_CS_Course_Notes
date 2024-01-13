import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parents {
    public static int[] findParents(int n) {
        int[] parents = new int[n];
        // TODO: implement this method

        return parents;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // 文件中第一个数字是数组长度，接下来N个数字才是数组元素。
        // 请根据实际情况更改文件路径
        File input = new File("src/test_data/Q1/A1.in");
        if (!input.exists()) {
            System.out.println("File isn't exist");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        int n = in.nextInt();  //the number of tree nodes
        //TODO: Store the edges in the tree
        //Your code here

        //TODO: Pass your parameter
        int[] parents = findParents(n, parameter);

        File output = new File("src/test_data/Q1/A1.out");
        in = new Scanner(output);
        boolean flag = true;

        for (int i = 0; i < n; i++) {
            if (in.nextInt() != parents[i]) {
                flag = false;
                break;
            }
        }
        System.out.println(flag);
    }
}
