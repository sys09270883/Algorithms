/*
https://www.acmicpc.net/problem/16566
[문제]
철수와 민수는 카드 게임을 즐겨한다. 이 카드 게임의 규칙은 다음과 같다.

N개의 빨간색 카드가 있다. 각각의 카드는 순서대로 1부터 N까지 번호가 매겨져 있다. 이 중에서 M개의 카드를 고른다.
N개의 파란색 카드가 있다. 각각의 카드는 순서대로 1부터 N까지 번호가 매겨져 있다. 이 중에서 빨간색에서 고른 번호와 같은 파란색 카드 M개를 고른다.
철수는 빨간색 카드를 가지고 민수는 파란색 카드를 가진다.
철수와 민수는 고른 카드 중에 1장을 뒤집어진 상태로 낸다. 그리고 카드를 다시 뒤집어서 번호가 큰 사람이 이긴다. 이 동작을 K번 해서 더 많이 이긴 사람이 최종적으로 승리한다. 
한 번 낸 카드는 반드시 버려야 한다.
철수는 뛰어난 마술사이기 때문에 본인이 낼 카드를 마음대로 조작할 수 있다. 즉, 카드를 버리고 민수 몰래 다시 들고 온다거나 민수한테 없는 카드를 내기도 한다.

민수는 뛰어난 심리학자이기 때문에 철수가 낼 카드를 알아낼 수 있다. 그래서 민수는 철수가 낼 카드보다 큰 카드가 있다면 그 카드들 중 가장 작은 카드를 내기로 했다.

K번 동안 철수가 낼 카드가 입력으로 주어진다. 그렇다면 민수가 어떤 카드를 낼지 출력하라. 단, 민수가 카드를 내지 못하는 경우는 없다고 가정한다.

[입력]
첫째 줄에 세 개의 자연수 N, M, K가 주어진다. (1 ≤ M ≤ N ≤ 4,000,000, 1 ≤ K ≤ min(M, 10,000))

다음 줄에 카드의 번호를 나타내는 M개의 자연수가 주어진다. 각각의 수들은 1 이상이고 N 이하이며 서로 다르다.

다음 줄에 K개의 자연수가 주어진다. i번째 수는 철수가 i번째로 내는 카드의 번호이다. 철수가 내는 카드 역시 1 이상 N 이하이다.

[출력]
K줄에 걸쳐서 수를 출력한다. i번째 줄에는 민수가 i번째로 낼 카드의 번호가 출력되어야 한다.

[풀이]
1. 카드 번호를 입력 받고 오름차순으로 정렬한다.
2. 각 쿼리에 대해 이분탐색으로 인덱스를 찾는다.
  2-1. 인덱스가 양수인 경우 해당 인덱스보다 큰 인덱스 중 방문하지 않은 인덱스를 출력한다.
  2-2. 인덱스가 음수인 경우 -(해당 인덱스 + 1)보다 큰 인덱스 중 방문하지 않은 인덱스를 출력한다.
3. 방문체크를 한 후 해당 과정을 반복한다. 

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N, M, K;
        N = io.nextInt();
        M = io.nextInt();
        K = io.nextInt();
        int[] arr = new int[M + 1];
        StringBuilder res = new StringBuilder();
        boolean[] visited = new boolean[M + 1];

        for (int i = 1; i < M + 1; i++) {
            arr[i] = io.nextInt();
        }

        Arrays.sort(arr);

        for (int i = 0; i < K; i++) {
            int idx = Arrays.binarySearch(arr, io.nextInt());

            if (idx < 0) {
                int find = -(idx + 1);

                while (true) {
                    if (!visited[find]) {
                        visited[find] = true;
                        break;
                    }

                    find++;
                }

                res.append(arr[find]).append('\n');
            }

            else {
                int find = idx + 1;

                while (true) {
                    if (!visited[find]) {
                        visited[find] = true;
                        break;
                    }

                    find++;
                }

                res.append(arr[find]).append('\n');
            }
        }

        io.write(res);
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
