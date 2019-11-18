/*
https://www.acmicpc.net/problem/16562
[문제]
19학번 이준석은 학생이 N명인 학교에 입학을 했다. 준석이는 입학을 맞아 모든 학생과 친구가 되고 싶어한다. 
하지만 준석이는 평생 컴퓨터랑만 대화를 하며 살아왔기 때문에 사람과 말을 하는 법을 모른다. 그런 준석이에게도 희망이 있다. 바로 친구비다!

학생 i에게 Ai만큼의 돈을 주면 그 학생은 1달간 친구가 되어준다! 준석이에게는 총 k원의 돈이 있고 그 돈을 이용해서 친구를 사귀기로 했다. 
막상 친구를 사귀다 보면 돈이 부족해질 것 같다는 생각을 하게 되었다. 그래서 준석이는 “친구의 친구는 친구다”를 이용하기로 했다.

준석이는 이제 모든 친구에게 돈을 주지 않아도 된다!

위와 같은 논리를 사용했을 때, 가장 적은 비용으로 모든 사람과 친구가 되는 방법을 구하라.

[입력]
첫 줄에 학생 수 N (1 ≤ N ≤ 10,000)과 친구관계 수 M (0 ≤ M ≤ 10,000), 가지고 있는 돈 k (1 ≤ k ≤ 10,000,000)가 주어진다.

두번째 줄에 N개의 각각의 학생이 원하는 친구비 Ai가 주어진다. (1 ≤ Ai ≤ 10,000, 1 ≤ i ≤ N)

다음 M개의 줄에는 숫자 v, w가 주어진다. 이것은 학생 v와 학생 w가 서로 친구라는 뜻이다.

[출력]
준석이가 모든 학생을 친구로 만들 수 있다면, 친구로 만드는데 드는 최소비용을 출력한다. 만약 친구를 다 사귈 수 없다면, “Oh no”(따옴표 제거)를 출력한다.

[풀이]
1. 친구관계를 양방향 인접리스트에 저장한다.
2. DFS 탐색을 하면서 친구 관계를 유지할 수 있는 최소 친구비를 구한다.
3. 최소 친구비를 더하면서 그 값이 K보다 클 경우 "Oh no"를 출력하고 종료한다.
4. 그 외의 경우 최소 친구비의 합을 출력한다.

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