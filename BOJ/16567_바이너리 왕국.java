/*
https://www.acmicpc.net/problem/16567
[����]
���̳ʸ� �ձ��� �ҽ��� ���ε��� ���� ���̳ʸ� ���� û���Ѵ�. ���̳ʸ� ���� N���� 0 �Ǵ� 1�� �̷���� �����̴�.

0�� ������ ĭ, 1�� ������ ĭ�� �ǹ��Ѵ�.

�׵��� "flip"�̶�� ������� ����ؼ� û�Ҹ� �Ѵ�. �̰��� ���ӵ� ������ ĭ�� �����ϰ� ����� ����̴�. ��, ���ӵ� 1�� ��� 0���� �����.

���̳ʸ� �ձ��� �Ǵ��� ���� ���� ���ε鿡�� M���� �÷��� ������ ���� ����̴�. �÷ÿ��� 2���� ������ �ִµ� ������ ����.

"0": ���� ���� ��� ĭ�� �����ϰ� ����� ���� "flip"�� �ּ� Ƚ���� ���ε鿡�� ũ�� ��ġ�� �Ѵ�.
"1 i": ���̳ʸ� ���� i��° ĭ�� ��������. ��, �̹� �������� �ִٸ� �ƹ� �ϵ� �Ͼ�� �ʴ´�.
���̳ʸ� �ձ��� �ҽ��� ���ε��� ���� ��ħ���� ����϶�.

[�Է�]
ù° �ٿ� ���̳ʸ� ���� ĭ�� ���� N, �÷��� ���� M�� �־�����. (1 �� N, M �� 1,000,000)

��° �ٿ� N���� ���� ���̳ʸ� ���� ���°� �־�����.

�״��� M���� �ٿ� ���ļ� �÷��� �־�����. �̶� 0�� �÷��� "0", 1�� �÷��� "1 i"�� ���� �־�����. (1 �� i �� N)

[���]
0�� �÷��� �־����� ��, ���ε��� ��ħ�� �������� �����Ͽ� ����϶�.

[Ǯ��]
�Է¹��� �� �Է��� 1�� ��� �ش� ���ǿ� ���� ó���Ѵ�.
  1-1.�Է��� �ε��� idx�� 1 < idx < N�� �� : �� ���� 1�̸� cnt--, �� ���� 0�̸� cnt++
  1-2. �Է��� �ε��� idx�� 1�� ��, arr[idx + 1]�� 1�̸� cnt--, 0�̸� cnt++
  1-3. �Է��� �ε��� idx�� N�� ��, arr[idx - 1]�� 1�̸� cnt--, 0�̸� cnt++

2. �÷ÿ� ���� ó���Ѵ�.
  2-1. �÷��� 0�� �� : cnt�� ���
  2-1. �÷��� 1�� �� : 1-1 ~ 1-3 ������ �ݺ�

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
