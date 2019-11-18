/*
https://www.acmicpc.net/problem/16565
[문제]
정연이는 트럼프 카드 (Playing Card)로 할 수 있는 새로운 게임을 만들기로 결심했다.

우선 이 게임은 딜러와 플레이어가 1:1로 플레이한다. 그리고 플레이어는 놓여진 52장의 트럼프 카드에서 N장의 카드를 뽑는다. 
뽑은 카드들로 "포카드 (four of a kind)" 족보를 만들 수 있다면 플레이어의 승리, 만들 수 없다면 딜러의 승리로 게임이 끝난다. 
그러나 정연이는 아직 공정한 게임을 위한, 뽑는 카드의 수 N을 결정하지 못하였다.

정연이가 쉽게 결정을 내릴 수 있도록, N개의 카드를 뽑았을 때 플레이어가 이기는 경우의 수를 출력하는 프로그램을 작성해주자.

트럼프 카드는 다음과 같은 52장의 카드로 구성된다.



Figure: 트럼프 카드 (Playing Card)의 구성

문양 4개: ♥, ♠, ◆, ♣, 숫자 13개: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K

총 4 x 13 = 52장

포카드 (four of a kind)는 뽑은 N장의 카드 중에 "같은 숫자를 가진, 다른 문양의 4장의 카드"가 존재하는 경우를 의미한다. 
또한 플레이어가 이기는 경우의 수는 N장의 카드에 이러한 카드 조합을 1쌍 이상 포함하고 있는 경우의 수를 의미한다.

[입력]
첫째 줄에 뽑는 카드의 수 N이 주어진다. (1 ≤ N ≤ 52)

[출력]
첫째 줄에 N장의 카드를 뽑았을 때, 플레이어가 이기는 경우의 수를 10,007로 나눈 나머지를 출력하라.

[풀이]
포카드를 미리 뽑고 나머지 카드를 뽑는 경우의 수를 따진다.
나머지 카드를 뽑을 때, 포카드가 한 쌍 이상이 뽑히는 경우가 중복으로 체크될 수 있기 때문에 이 부분을 제거해야 한다.
일반항은 sum(C(13, i)*C(52 - 4i)*(-1)^2) (i = [1, N/4])이다.
수의 연산이 long의 표현 범위를 넘을 수 있어 BigInteger로 계산하고 최종적으로 10007로 나누어 준다.
 + 이항계수를 구할 때 dp로 구하는 것이 일반적이지만 귀찮아서 BigInteger로 함.

*/
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        BigInteger res = BigInteger.ZERO;

        for (int i = 1; i <= N / 4; i++) {
            if (i % 2 == 0)
                res = res.add(combination(13, i).multiply(combination(52 - 4 * i, N - 4 * i)).multiply(BigInteger.valueOf(-1)));

            else
                res = res.add(combination(13, i).multiply(combination(52 - 4 * i, N - 4 * i)));
        }

        io.write(res.mod(BigInteger.valueOf(10007)).toString());
    }

    private static BigInteger combination(int m, int n) {
        return factorial(m).divide(factorial(m - n)).divide(factorial(n));
    }

    private static BigInteger factorial(int n) {
        BigInteger res = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }

        return res;
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