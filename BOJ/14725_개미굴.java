import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static StringBuilder res;

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        TreeMap<String, Node> tree = new TreeMap<>();
        res = new StringBuilder();

        for (int i = 0; i < N; i++) {
            TreeMap<String, Node> subtree = tree;
            int sz = io.nextInt();
            List<String> childList = new ArrayList<>(sz);
            for (int j = 0; j < sz; j++) {
                childList.add(io.next());
            }

            for (String child : childList) {
                if (!subtree.containsKey(child))
                    subtree.put(child, new Node());
                subtree = subtree.get(child).childs;
            }
        }

        for (String s : tree.keySet()) {
            DFS(s, 0, tree);
        }
        io.write(res);
    }

    private static void DFS(String child, int depth, TreeMap<String, Node> tree) {
        for (int i = 0; i < depth * 2; i++) {
            res.append('-');
        }
        res.append(child).append('\n');

        for (String subchild : tree.get(child).childs.keySet()) {
            DFS(subchild, depth + 1, tree.get(child).childs);
        }
    }

}

class Node {
    TreeMap<String, Node> childs;

    public Node() {
        this.childs = new TreeMap<>();
    }
}

class FastIO {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    FastIO() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }

        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        return br.readLine();
    }

    void write(double d) throws IOException {
        bw.write(String.valueOf(d));
        close();
    }

    void write(char c) throws IOException {
        bw.write(c);
        close();
    }

    void write(int i) throws IOException {
        bw.write(String.valueOf(i));
        close();
    }

    void write(long l) throws IOException {
        bw.write(String.valueOf(l));
        close();
    }

    void write(StringBuilder sb) throws IOException {
        bw.write(sb.toString());
        close();
    }

    void write(String str) throws IOException {
        bw.write(str.trim());
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}