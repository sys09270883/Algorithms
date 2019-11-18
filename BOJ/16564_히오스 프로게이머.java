/*
https://www.acmicpc.net/problem/16564
[����]
�����̴� Heroes of the Storm ���ΰ��̸� �������̴�.

�� ���ӿ��� �� N���� ĳ���Ͱ� �ִ�. �׸��� ���� �� ĳ������ ������ Xi�̴�. �����̴� ������ ������ ���� ������, ������ �ִ� ���� K��ŭ �ø� �� �ִ�.

�� ��ǥ���� T =min(Xi) (1 �� i �� N)��� �����ϸ�, ������ ���� ������ �����̰� �޼��� �� �ִ� �ִ� �� ��ǥ���� T�� �����ΰ�?

���� ���, N = 3, X1= 10, X2= 20, X3= 15�̰� K = 10�� ��, X1�� 7��ŭ �ø��� X3�� 2��ŭ �ø��� �ּ� ���� Xi�� 17�� �ȴ�. ���� �� ��ǥ���� T�� 17�̴�. �� ���ó�� ������ ���� K���� ���� �ø� ���� �ִ�.

[�Է�]
ù° �ٿ��� ĳ������ ���� N, �ø� �� �ִ� ���� ���� K�� �־�����. (1 �� N ��1,000,000, 1 �� K �� 1,000,000,000)

���� N���� �ٿ��� ���� �� ĳ������ ������ X1, X2, X3, ... , Xn ���� �־�����. (1 �� Xi �� 1,000,000,000)

[���]
������ �ִ� �� ��ǥ���� T�� ����Ѵ�.

[Ǯ��]
�� ĳ���� �� ���� ���� ���� ���� ���� �� ���̿� �־��� ������ �����ϴ� ���� ������ �����Ƿ� �̺�Ž������ �����Ѵ�.
������ �ּ� ���� �ִ� ���� �߰������� ���� ���Ѵ�.
  i. ���� 0���� ũ�� K������ ���, T < m�̸� T�� �����Ѵ�. �׸��� low�� m + 1�� �����Ѵ�.
  ii. ���� K���� Ŭ ���, high�� m - 1�� �����Ѵ�.
  iii. �� ������ low <= high�� ���� �ݺ��Ѵ�.

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