import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Problem02 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int I = in.nextInt();

        int[] ms = new int[n];
        for (int i = 0; i < n; i++){
            ms[i] = in.nextInt();
        }

        HashMap<String,Integer> stmMap = new HashMap<>();
        ArrayList<String> stm = new ArrayList<>();
        int[] as = new int[n];
        int[] bs = new int[n];
        boolean isBiggerThanZero = true;
        MaxHeap heap1 = new MaxHeap();
        MinHeap heap2 = new MinHeap();


        for (int i = 0; i < n; i++){
            String str = in.nextLine();

            if (str.startsWith("1")){
                stm.add(str.substring(2));
                putIntoMap(str.substring(2),stmMap);
                addElement(str.substring(2).length(), heap1, heap2);
                as[i] = counterStatements(heap1); // 计算a
                // 计算b，但b在此情况不会变
                if (i == 0){
                    bs[i] = 0;
                } else {
                    bs[i] = bs[i - 1];
                }
            } else if (str.startsWith("2")){
                I++;
                int print = counterStatements(heap1);
                out.println(print); // 打印
                if (I < print){
                    I -= stm.size();
                }
                if (I < 0) isBiggerThanZero = false;
                // 计算a，但a在此情况不变
                if (i == 0){
                    as[i] = 0;
                } else {
                    as[i] = as[i - 1];
                }
                // 计算b
                if (i == 0){
                    bs[i] = 0;
                } else {
                    bs[i] = bs[i - 1] + pairMatch(str.substring(2), stmMap);
                }
            } else if (str.startsWith("3")){
                // 计算a和b，但此情况下a和b都不变
                if (i == 0){
                    as[i] = 0;
                } else {
                    as[i] = as[i - 1];
                }
                if (i == 0){
                    bs[i] = 0;
                } else {
                    bs[i] = bs[i - 1];
                }
                out.println(bs[i]);
            }

        }

        // 计算判断score并结合isBiggerThanZero输出结果
        int aSum = 0;
        int bSum = 0;
        long mSum = 0;
        for (int i = 0; i < n; i++){
            aSum += as[i];
        }
        for (int i = 0; i < n; i++){
            bSum += bs[i];
        }
        for (int i = 0; i < n; i++){
            mSum += ms[i];
        }
        if (aSum * bSum != 0 && mSum < 0 || !isBiggerThanZero){
            out.println("Fail");
        } else {
            out.println("Qi Fei");
        }
        out.close();
    }

    // 计算中间长度
    public static int counterStatements(MaxHeap heap1){
        return heap1.getMax();
    }

    // 添加一个元素
    public static void addElement(int k, MaxHeap heap1, MinHeap heap2){
        if (heap1.size() == 0 || heap1.getMax() > k){
            heap1.push(k);
            if (heap1.size() > heap2.size() + 1){
                heap2.push(heap1.getMax());
                heap1.pop();
            }
        } else {
            heap2.push(k);
            if (heap1.size() < heap2.size()){
                heap1.push(heap2.getMin());
                heap2.pop();
            }
        }
    }

    // 将一个statement放进HashMap
    public static void putIntoMap(String stm, HashMap<String,Integer> stmMap){
        if (stmMap.containsKey(stm)){
            stmMap.replace(stm, stmMap.get(stm) + 1);
        } else {
            stmMap.put(stm, 1);
        }
    }

    // 将truth bullet与stmMap进行匹配，返回增加的键值对数量
    public static int pairMatch(String tb, HashMap<String,Integer> stmMap){
        if (stmMap.containsKey(tb)){
            return stmMap.get(tb);
        }
        return 0;
    }

}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}

class MinHeap{
    private static final int MAXSIZE = 10010;
    private int[] a = new int[MAXSIZE];
    private void swap(int x,int y){
        a[x] = a[x]^a[y];
        a[y] = a[y]^a[x];
        a[x] = a[x]^a[y];
    }
    private void up(){
        int p = a[0];
        while(p > 1){
            if (a[p] < a[p/2]){
                swap(p,p/2);
                p = p / 2;
            }
            else
                break;
        }
    }

    public void push(int k){
        a[++a[0]] = k;
        up();
    }
    public void pop(){
        int s = 2, t = 1;
        a[1] = a[a[0]--];
        while (s <= a[0]){
            if (s <= a[0] - 1 && a[s+1] < a[s])
                ++s;
            if (a[s] < a[t]){
                swap(s, t);
                t = s;
                s*=2;
            }
            else
                break;
        }
    }
    public int getMin(){
        return a[1];
    }
    public int size(){
        return a[0];
    }
}

class MaxHeap{
    private static final int MAXSIZE = 10010;
    private int[] a = new int[MAXSIZE];
    private void swap(int x,int y){
        a[x] = a[x]^a[y];
        a[y] = a[y]^a[x];
        a[x] = a[x]^a[y];
    }
    private void up(){
        int p = a[0];
        while(p > 1){
            if (a[p] > a[p/2]){
                swap(p,p/2);
                p = p / 2;
            }
            else
                break;
        }
    }

    public void push(int k){
        a[++a[0]] = k;
        up();
    }
    public void pop(){
        int s = 2, t = 1;
        a[1] = a[a[0]--];
        while (s <= a[0]){
            if (s <= a[0] - 1 && a[s+1] > a[s])
                ++s;
            if (a[s] > a[t]){
                swap(s,t);
                t = s;
                s*=2;
            }
            else
                break;
        }
    }
    public int getMax(){
        return a[1];
    }
    public int size(){
        return a[0];
    }
}
