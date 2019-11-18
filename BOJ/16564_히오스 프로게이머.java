/*
https://www.acmicpc.net/problem/16564
[문제]
성권이는 Heroes of the Storm 프로게이머 지망생이다.

이 게임에는 총 N개의 캐릭터가 있다. 그리고 현재 각 캐릭터의 레벨은 Xi이다. 성권이는 앞으로 게임이 끝날 때까지, 레벨을 최대 총합 K만큼 올릴 수 있다.

팀 목표레벨 T =min(Xi) (1 ≤ i ≤ N)라고 정의하면, 게임이 끝날 때까지 성권이가 달성할 수 있는 최대 팀 목표레벨 T는 무엇인가?

예를 들어, N = 3, X1= 10, X2= 20, X3= 15이고 K = 10일 때, X1을 7만큼 올리고 X3을 2만큼 올리면 최소 레벨 Xi는 17이 된다. 따라서 팀 목표레벨 T는 17이다. 이 경우처럼 레벨을 총합 K보다 적게 올릴 수도 있다.

[입력]
첫째 줄에는 캐릭터의 개수 N, 올릴 수 있는 레벨 총합 K가 주어진다. (1 ≤ N ≤1,000,000, 1 ≤ K ≤ 1,000,000,000)

다음 N개의 줄에는 현재 각 캐릭터의 레벨이 X1, X2, X3, ... , Xn 으로 주어진다. (1 ≤ Xi ≤ 1,000,000,000)

[출력]
가능한 최대 팀 목표레벨 T를 출력한다.

[풀이]
각 캐릭터 중 가장 낮은 값과 가장 높은 값 사이에 주어진 조건을 만족하는 값이 무조건 있으므로 이분탐색으로 접근한다.
레벨의 최소 값과 최대 값의 중간값으로 값을 비교한다.
  i. 값이 0보다 크고 K이하일 경우, T < m이면 T를 갱신한다. 그리고 low를 m + 1로 갱신한다.
  ii. 값이 K보다 클 경우, high를 m - 1로 갱신한다.
  iii. 위 과정을 low <= high일 동안 반복한다.

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N, K, low = 1, high = 1000000000, T = -1;
        N = io.nextInt();
        K = io.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = io.nextInt();
        }

        while (low <= high) {
            int m = (low + high) >> 1;
            int sum = 0;

            for (int i = 0; i < N; i++) {
                if (m > arr[i]) {
                    sum += m - arr[i];

                    if (sum > K) {
                        sum = 0;
                        break;
                    }
                }
            }

            if (0 < sum && sum <= K) {
                if (T < m)
                    T = m;

                low = m + 1;
            }

            else
                high = m - 1;
        }

        io.write(T);
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