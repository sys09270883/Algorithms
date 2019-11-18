/*
https://www.acmicpc.net/problem/16568
[����]
�ѱ��̴� ���� �������̸�, �������� ��ȥ�� �ޱ� ���� ���� ���ִ�. �ѱ��̴� ������ ���� ��� ���� �μ��� ���ȴ�. 
�׸��� �ְ�� ������ ����ī�� ��ȥ�� �ޱ� ���ؼ� ��ġ�⸦ �ϱ�� ����ߴ�.

�ѱ����� �տ� N���� ������� �� ���ִ�. 1�ʰ� ���� ������ ���� �� �� ����� ��ȥ�� �ް� ���� ����. �׸��� 1�ʸ��� �ѱ��̴� ������ ���� �ൿ�� �� �� �ִ�.

��ٸ���
a�� ������ ���� (�տ� �ּ� a�� ���� ��)
b�� ������ ���� (�տ� �ּ� b�� ���� ��)
��, �ѱ��̴� ��ġ�⿡�� ���� �ձ⶧����, ��� �ൿ�� 0�ʸ��� �� �� �ִ�.

���� ��� N = 5, a = 1, b = 2��� ����. 5�ʵ��� ��ٸ��⸸ �ϸ� ���� �� �� ����� ������ ������ ���� �� �տ� ���ֱ���� 5�ʰ� �ɸ���. 
������ �� �� �� ���� ���� ���� �ѱ��̰� 2�� ������ ��ġ��, �� ���� �� ���� ���� ���� 1�� ������ ��ġ���ϸ� 2�ʸ��� ���� �� �տ� ���� �ȴ�. 
������ ���� 1�ʿ� �� �� �� ���� ���� 2�� ������ ��ġ���ϰ� �� �� �� ���� ���� 1���� ���´�. �� �� 2�� ������ ��ġ��� �Ұ����ϴ�.

�ѱ��̰� ���� �� �տ� ���������� �ּ� �� �ʰ� �ɸ��°�?

[�Է�]
ù° �ٿ� N, a, b�� �־�����. (0 �� N �� 1,000,000, 0 �� a, b �� N)

[���]
�ѱ��̰� �� �տ� ���µ� �ɸ��� �ּ� �ð��� ����Ѵ�.

[Ǯ��]
N���� 0���� BFSŽ���� �Ѵ�.
 + DP�ε� Ǯ�̰� �����ϴ�.
 
*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, jumpA, jumpB;
    static int[] arr;
    static boolean[] visited;
    static int[] dx = new int[3];

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        jumpA = io.nextInt();
        jumpB = io.nextInt();
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        dx[0] = 1;
        dx[1] = 1 + jumpA;
        dx[2] = 1 + jumpB;

        io.write(BFS());
    }

    private static int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(N, 0));
        visited[N] = true;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int cur = tmp.idx;
            int cnt = tmp.cnt;

            if (cur == 0)
                return cnt;

            for (int i = 0; i < 3; i++) {
                int next = cur - dx[i];

                if (next < 0 || visited[next])
                    continue;

                visited[next] = true;
                queue.add(new Node(next, cnt + 1));
            }
        }

        return -1;
    }

}

class Node {
    int idx, cnt;

    public Node(int idx, int cnt) {
        super();
        this.idx = idx;
        this.cnt = cnt;
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

    String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    void write(double d) throws IOException {
        bw.write(String.valueOf(d));
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
        bw.write(sb.toString().trim());
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
