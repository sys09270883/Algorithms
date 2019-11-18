/*
https://www.acmicpc.net/problem/16565
[����]
�����̴� Ʈ���� ī�� (Playing Card)�� �� �� �ִ� ���ο� ������ ������ ����ߴ�.

�켱 �� ������ ������ �÷��̾ 1:1�� �÷����Ѵ�. �׸��� �÷��̾�� ������ 52���� Ʈ���� ī�忡�� N���� ī�带 �̴´�. 
���� ī���� "��ī�� (four of a kind)" ������ ���� �� �ִٸ� �÷��̾��� �¸�, ���� �� ���ٸ� ������ �¸��� ������ ������. 
�׷��� �����̴� ���� ������ ������ ����, �̴� ī���� �� N�� �������� ���Ͽ���.

�����̰� ���� ������ ���� �� �ֵ���, N���� ī�带 �̾��� �� �÷��̾ �̱�� ����� ���� ����ϴ� ���α׷��� �ۼ�������.

Ʈ���� ī��� ������ ���� 52���� ī��� �����ȴ�.



Figure: Ʈ���� ī�� (Playing Card)�� ����

���� 4��: ��, ��, ��, ��, ���� 13��: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K

�� 4 x 13 = 52��

��ī�� (four of a kind)�� ���� N���� ī�� �߿� "���� ���ڸ� ����, �ٸ� ������ 4���� ī��"�� �����ϴ� ��츦 �ǹ��Ѵ�. 
���� �÷��̾ �̱�� ����� ���� N���� ī�忡 �̷��� ī�� ������ 1�� �̻� �����ϰ� �ִ� ����� ���� �ǹ��Ѵ�.

[�Է�]
ù° �ٿ� �̴� ī���� �� N�� �־�����. (1 �� N �� 52)

[���]
ù° �ٿ� N���� ī�带 �̾��� ��, �÷��̾ �̱�� ����� ���� 10,007�� ���� �������� ����϶�.

[Ǯ��]
��ī�带 �̸� �̰� ������ ī�带 �̴� ����� ���� ������.
������ ī�带 ���� ��, ��ī�尡 �� �� �̻��� ������ ��찡 �ߺ����� üũ�� �� �ֱ� ������ �� �κ��� �����ؾ� �Ѵ�.
�Ϲ����� sum(C(13, i)*C(52 - 4i)*(-1)^2) (i = [1, N/4])�̴�.
���� ������ long�� ǥ�� ������ ���� �� �־� BigInteger�� ����ϰ� ���������� 10007�� ������ �ش�.
 + ���װ���� ���� �� dp�� ���ϴ� ���� �Ϲ��������� �����Ƽ� BigInteger�� ��.

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