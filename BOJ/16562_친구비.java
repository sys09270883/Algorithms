/*
https://www.acmicpc.net/problem/16562
[����]
19�й� ���ؼ��� �л��� N���� �б��� ������ �ߴ�. �ؼ��̴� ������ �¾� ��� �л��� ģ���� �ǰ� �;��Ѵ�. 
������ �ؼ��̴� ��� ��ǻ�Ͷ��� ��ȭ�� �ϸ� ��ƿԱ� ������ ����� ���� �ϴ� ���� �𸥴�. �׷� �ؼ��̿��Ե� ����� �ִ�. �ٷ� ģ�����!

�л� i���� Ai��ŭ�� ���� �ָ� �� �л��� 1�ް� ģ���� �Ǿ��ش�! �ؼ��̿��Դ� �� k���� ���� �ְ� �� ���� �̿��ؼ� ģ���� ��ͱ�� �ߴ�. 
���� ģ���� ��ʹ� ���� ���� �������� �� ���ٴ� ������ �ϰ� �Ǿ���. �׷��� �ؼ��̴� ��ģ���� ģ���� ģ���١��� �̿��ϱ�� �ߴ�.

�ؼ��̴� ���� ��� ģ������ ���� ���� �ʾƵ� �ȴ�!

���� ���� ���� ������� ��, ���� ���� ������� ��� ����� ģ���� �Ǵ� ����� ���϶�.

[�Է�]
ù �ٿ� �л� �� N (1 �� N �� 10,000)�� ģ������ �� M (0 �� M �� 10,000), ������ �ִ� �� k (1 �� k �� 10,000,000)�� �־�����.

�ι�° �ٿ� N���� ������ �л��� ���ϴ� ģ���� Ai�� �־�����. (1 �� Ai �� 10,000, 1 �� i �� N)

���� M���� �ٿ��� ���� v, w�� �־�����. �̰��� �л� v�� �л� w�� ���� ģ����� ���̴�.

[���]
�ؼ��̰� ��� �л��� ģ���� ���� �� �ִٸ�, ģ���� ����µ� ��� �ּҺ���� ����Ѵ�. ���� ģ���� �� ��� �� ���ٸ�, ��Oh no��(����ǥ ����)�� ����Ѵ�.

[Ǯ��]
1. ģ�����踦 ����� ��������Ʈ�� �����Ѵ�.
2. DFS Ž���� �ϸ鼭 ģ�� ���踦 ������ �� �ִ� �ּ� ģ���� ���Ѵ�.
3. �ּ� ģ���� ���ϸ鼭 �� ���� K���� Ŭ ��� "Oh no"�� ����ϰ� �����Ѵ�.
4. �� ���� ��� �ּ� ģ������ ���� ����Ѵ�.

*/

import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, K, cost;
    static int[] costs;
    static List<List<Integer>> adj;
    static boolean[] visited;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        M = io.nextInt();
        K = io.nextInt();
        costs = new int[N + 1];
        adj = new ArrayList<List<Integer>>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<Integer>());
        }
        visited = new boolean[N + 1];
        int ans = 0;

        for (int i = 1; i < N + 1; i++) {
            costs[i] = io.nextInt();
        }

        for (int i = 0; i < M; i++) {
            int v = io.nextInt();
            int w = io.nextInt();
            adj.get(v).add(w);
            adj.get(w).add(v);
        }

        for (int i = 1; i < N + 1; i++) {
            cost = Integer.MAX_VALUE;

            if (!visited[i]) {
                DFS(i);
                ans += cost;

                if (ans > K) {
                    io.write("Oh no");
                    return;
                }
            }
        }

        io.write(ans);
    }

    private static void DFS(int idx) {
        visited[idx] = true;

        if (costs[idx] < cost)
            cost = costs[idx];

        for (int next : adj.get(idx)) {
            if (!visited[next])
                DFS(next);
        }
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