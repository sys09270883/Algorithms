/*
https://www.acmicpc.net/problem/16567
[문제]
바이너리 왕국의 불쌍한 하인들은 매일 바이너리 길을 청소한다. 바이너리 길은 N개의 0 또는 1로 이루어진 수열이다.

0은 깨끗한 칸, 1은 더러운 칸을 의미한다.

그들은 "flip"이라는 기술만을 사용해서 청소를 한다. 이것은 연속된 더러운 칸을 깨끗하게 만드는 기술이다. 즉, 연속된 1을 모두 0으로 만든다.

바이너리 왕국의 악덕한 왕은 매일 하인들에게 M개의 시련을 내리는 것이 취미이다. 시련에는 2가지 종류가 있는데 다음과 같다.

"0": 현재 길의 모든 칸을 깨끗하게 만들기 위한 "flip"의 최소 횟수를 하인들에게 크게 외치게 한다.
"1 i": 바이너리 길의 i번째 칸을 더럽힌다. 단, 이미 더럽혀져 있다면 아무 일도 일어나지 않는다.
바이너리 왕국의 불쌍한 하인들의 슬픈 외침들을 출력하라.

[입력]
첫째 줄에 바이너리 길의 칸의 개수 N, 시련의 개수 M이 주어진다. (1 ≤ N, M ≤ 1,000,000)

둘째 줄에 N개의 현재 바이너리 길의 상태가 주어진다.

그다음 M개의 줄에 걸쳐서 시련이 주어진다. 이때 0번 시련은 "0", 1번 시련은 "1 i"와 같이 주어진다. (1 ≤ i ≤ N)

[출력]
0번 시련이 주어졌을 때, 하인들의 외침을 개행으로 구분하여 출력하라.

[풀이]
입력받을 때 입력이 1일 경우 해당 조건에 따라 처리한다.
  1-1.입력의 인덱스 idx가 1 < idx < N일 때 : 양 옆이 1이면 cnt--, 양 옆이 0이면 cnt++
  1-2. 입력의 인덱스 idx가 1일 때, arr[idx + 1]이 1이면 cnt--, 0이면 cnt++
  1-3. 입력의 인덱스 idx가 N일 때, arr[idx - 1]이 1이면 cnt--, 0이면 cnt++

2. 시련에 따라 처리한다.
  2-1. 시련이 0일 때 : cnt를 출력
  2-1. 시련이 1일 때 : 1-1 ~ 1-3 과정을 반복

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        int M = io.nextInt();
        int[] arr = new int[N + 1];
        int cnt = 0;
        StringBuilder res = new StringBuilder();

        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();

            if (arr[i] == 1) {
                if (1 < i && i < N) {
                    if (arr[i - 1] == 1 && arr[i + 1] == 1)
                        cnt--;

                    else if (arr[i - 1] == 0 && arr [i + 1] == 0)
                        cnt++;
                }

                else if (i == 1) {
                    if (arr[i + 1] == 0)
                        cnt++;
                }

                else if (i == N) {
                    if (arr[i - 1] == 0)
                        cnt++;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int query = io.nextInt();

            if (query == 0)
                res.append(cnt).append('\n');

            else {
                int idx = io.nextInt();

                if (arr[idx] == 0) {
                    arr[idx] = 1;
                    if (1 < idx && idx < N) {
                        if (arr[idx - 1] == 1 && arr[idx + 1] == 1)
                            cnt--;

                        else if (arr[idx - 1] == 0 && arr [idx + 1] == 0)
                            cnt++;
                    }

                    else if (idx == 1) {
                        if (arr[idx + 1] == 0)
                            cnt++;
                    }

                    else if (idx == N) {
                        if (arr[idx - 1] == 0)
                            cnt++;
                    }
                }
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
