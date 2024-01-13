import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Paths {
    public static int findPaths() {
        int pathNumber = 0;
        // TODO: implement this method and modify the pathNumber

        return pathNumber;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // 文件中第一个数字是数组长度，接下来N个数字才是数组元素。
        // 请根据实际情况更改文件路径
        File input = new File("src/test_data/Q2/B1.in");
        if (!input.exists()) {
            System.out.println("File isn't exist");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        int n = in.nextInt(); //the number of tree nodes
        int num = in.nextInt();// the target number
        //TODO: Store the edges in the tree
        //Your code here

        //TODO: Pass your parameter
        int pathNmuber = findPaths(parameter);

        File output = new File("src/test_data/Q2/B1.out");
        in = new Scanner(output);
        boolean flag = in.nextInt() == pathNmuber;
        System.out.println(flag);
    }
}
