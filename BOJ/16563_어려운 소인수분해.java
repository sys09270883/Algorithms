/*
https://www.acmicpc.net/problem/16563
[����]
�����̴� ��ȸ�� ������ ������ ���ؼ� ����ϴٰ� ���μ����� ������ �����ؾ߰ڴٰ� ������ �Ծ���. �׷��� �� �̾߱⸦ ���� ������ ������ �������� ����� ���ϰ� �ߴ�.

"���μ�����? �װ� �ʹ� ���� �� �ƴϾ�?"

�����̴� ���μ������� ������� �˷��ְ��� ��û�� �ڽŰ��� ���� �������� 2�� 500�� ������ �ڿ��� N���� �ְ� ���μ����ظ� ���״�. 
�׷��� �������� ������ ����ϸ� ��������. ������ϴ� �������� ������ ����ؼ� �������� �̰͵� ���ٴ� ���� ��������!

[�Է�]
ù° �ٿ��� �ڿ����� ���� N (1 �� N �� 1,000,000)�� �־�����.

��° �ٿ��� �ڿ��� ki (2 �� ki �� 5,000,000, 1 �� i �� N)�� N�� �־�����.

[���]
N�ٿ� ���ļ� �ڿ��� ki�� ���μ����� ������������ ����϶�.

[Ǯ��]
1. �����佺�׳׽��� ü�� 5,000,000�� �����ٱ����� �Ҽ��� ���Ѵ�.
2. �� ������ ���ؼ� 5,000,000�� �����ٱ����� �Ҽ���� ���μ� ���ظ� �Ѵ�.
  i. �� �������� �Ҽ��� ������ �ָ鼭 ����Ѵ�.
  ii. �� �̻� ���������� �ʰų� �ش� ���� 5,000,000�� �����ٺ��� Ŭ ���, �ش� ���� ������ �Ҽ��̱� ������ �ش� ���� ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        int sqrtSize = (int) Math.sqrt(5000000);
        boolean[] isPrime = new boolean[sqrtSize + 1];
        Arrays.fill(isPrime, true);
        List<Integer> primeList = new ArrayList<Integer>();
        StringBuilder res = new StringBuilder();

        for (int i = 2; i <= sqrtSize; i++) {
            if (!isPrime[i])
                continue;

            for (int j = i << 1; j <= sqrtSize; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = 2; i <= sqrtSize; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }

        for (int i = 0; i < N; i++) {
            int num = io.nextInt();

            for (int p : primeList) {
                while (num % p == 0) {
                    num /= p;
                    res.append(p).append(' ');
                }
            }

            if (num >= sqrtSize)
                res.append(num);

            res.append('\n');
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