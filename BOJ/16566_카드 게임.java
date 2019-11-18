/*
https://www.acmicpc.net/problem/16566
[����]
ö���� �μ��� ī�� ������ ����Ѵ�. �� ī�� ������ ��Ģ�� ������ ����.

N���� ������ ī�尡 �ִ�. ������ ī��� ������� 1���� N���� ��ȣ�� �Ű��� �ִ�. �� �߿��� M���� ī�带 ����.
N���� �Ķ��� ī�尡 �ִ�. ������ ī��� ������� 1���� N���� ��ȣ�� �Ű��� �ִ�. �� �߿��� ���������� �� ��ȣ�� ���� �Ķ��� ī�� M���� ����.
ö���� ������ ī�带 ������ �μ��� �Ķ��� ī�带 ������.
ö���� �μ��� �� ī�� �߿� 1���� �������� ���·� ����. �׸��� ī�带 �ٽ� ����� ��ȣ�� ū ����� �̱��. �� ������ K�� �ؼ� �� ���� �̱� ����� ���������� �¸��Ѵ�. 
�� �� �� ī��� �ݵ�� ������ �Ѵ�.
ö���� �پ �������̱� ������ ������ �� ī�带 ������� ������ �� �ִ�. ��, ī�带 ������ �μ� ���� �ٽ� ��� �´ٰų� �μ����� ���� ī�带 ���⵵ �Ѵ�.

�μ��� �پ �ɸ������̱� ������ ö���� �� ī�带 �˾Ƴ� �� �ִ�. �׷��� �μ��� ö���� �� ī�庸�� ū ī�尡 �ִٸ� �� ī��� �� ���� ���� ī�带 ����� �ߴ�.

K�� ���� ö���� �� ī�尡 �Է����� �־�����. �׷��ٸ� �μ��� � ī�带 ���� ����϶�. ��, �μ��� ī�带 ���� ���ϴ� ���� ���ٰ� �����Ѵ�.

[�Է�]
ù° �ٿ� �� ���� �ڿ��� N, M, K�� �־�����. (1 �� M �� N �� 4,000,000, 1 �� K �� min(M, 10,000))

���� �ٿ� ī���� ��ȣ�� ��Ÿ���� M���� �ڿ����� �־�����. ������ ������ 1 �̻��̰� N �����̸� ���� �ٸ���.

���� �ٿ� K���� �ڿ����� �־�����. i��° ���� ö���� i��°�� ���� ī���� ��ȣ�̴�. ö���� ���� ī�� ���� 1 �̻� N �����̴�.

[���]
K�ٿ� ���ļ� ���� ����Ѵ�. i��° �ٿ��� �μ��� i��°�� �� ī���� ��ȣ�� ��µǾ�� �Ѵ�.

[Ǯ��]
1. ī�� ��ȣ�� �Է� �ް� ������������ �����Ѵ�.
2. �� ������ ���� �̺�Ž������ �ε����� ã�´�.
  2-1. �ε����� ����� ��� �ش� �ε������� ū �ε��� �� �湮���� ���� �ε����� ����Ѵ�.
  2-2. �ε����� ������ ��� -(�ش� �ε��� + 1)���� ū �ε��� �� �湮���� ���� �ε����� ����Ѵ�.
3. �湮üũ�� �� �� �ش� ������ �ݺ��Ѵ�. 

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
