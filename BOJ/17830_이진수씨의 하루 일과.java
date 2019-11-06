/*
https://www.acmicpc.net/problem/17830
[����]
������ ���� �������� ����Ѵ�. ���� �Ϸ� �ϰ��� �Ϸ� ���� ������ �� ���� �����س���, �� �� ���� ���� "������ ������"�� �����Ѵ�. 
�׸��� ���� ���̸� �� �� ��� "������ ������"�� ���� �� ���ڿ� �����Ѵ�. 
�׷��� �׷� ���������Ե� �÷��� ã�ƿ�����, ���̸� ��� ���� ���� ���߿� "������ ������"�� �ؾ������ �� ���̴�. 
������, ���� ���� ���� �Ϸ� ������ ���� �� �������� ���ؼ��� ���ǲ�� ����ϰ� �ִ�.

  

�� �� �������� A�� B��� ����. ���� ���� ����ϴ� ����� ������ ����. A�� B�� N���� ��Ʈ�� �̷���� �ִ�.
A�� ��� ��Ʈ�� 1�̴�. ������ B�� �Ϻ� ��Ʈ�� ������� ���Ѵ�. ���⼭ "������ ������"�� ��� ���� ������ �� �� �ִ�.

���� ���, N = 4��� A = 1111�̴�. ���⼭ B = ?00?��� �غ���. ?�� ������� ���ϴ� ��Ʈ�� �ǹ��Ѵ�. 
�׷��ٸ� B�� 0000, 0001, 1000, 1001�� �ϳ��� ���̴�. ���� �� ��� "������ ������"�� A��B, �� 0, 1111, 1111000, 10000111�� �ϳ��� ���̴�. 
B�� leading zero�� ������ �� ������, "������ ������"�� leading zero�� �����Ѵ�. 
��, B�� �� �� �ڸ��� 1�� �ƴ� �� ������, �������� "������ ������"�� ���� ������ A��B=0�� ���� �����ϸ� �� ���ڸ��� �ݵ�� 1�� �ǵ��� 0�� �����Ѵ�.

���� ���� "������ ������"�� ���� �ʹ� ũ�ų� ���� ���̸� ��� ���� �ʴ�. 
���� "������ ������"�� �� ��, ������ �ִ� �ڸ����� �ּ� �ڸ����� ���غ����� �Ѵ�. 
������, ���� ���� ���� �ϻ� ������ �������� ū �������� ������ ���ϴ� ���� �ſ� ��ƴٴ� ���� �˰� �ִ�. ���� �������� �� ���� ��� ��������.

[�Է�]
ù ��° �ٿ� �׽�Ʈ���̽��� ���� T(1 �� T �� 20)�� �־�����. 

�� ��° �ٺ��� T���� �ٿ� ����, A�� B�� ���� N(1 �� N �� 1,000,000)�� B�� �������� ���еǾ� �־�����. B�� 0, 1, ?�� �̷���� ������, ?�� �ش� ��Ʈ�� ������� ������ �ǹ��Ѵ�.

[���]
T���� �ٿ� ����, �� �׽�Ʈ���̽��� ���� "������ ������"�� �ִ� �ڸ����� �ּ� �ڸ����� �������� �����Ͽ� ����Ѵ�.

[Ǯ��]
1. ���ڿ��� ��� ���ڰ� 0�� ��쿡�� �׻� 1�̴�.
2. ���ڿ��� ó�� 1�� ���� �ڸ� ���ķ� 0�� �ִٸ� N + M - 1�̴�. (M�� ó�� ���� 1 ���� ������ ����)
3. �� ���� ��쿡�� N + M�̴�.

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();

        while (T-- > 0) {
            int N = io.nextInt();
            String B = io.next();

            String bigB = B.replace('?', '1');
            String smallB = B.replace('?', '0');

            res.append(getSize(bigB, N)).append(' ').append(getSize(smallB, N)).append('\n');
        }

        io.write(res);
    }

    private static int getSize(String str, int N) {
        int firstIdx = str.indexOf('1');
        int lastIdx = str.lastIndexOf('1');

        if (firstIdx < 0)
            return 1;

        else if (firstIdx == lastIdx)
            return N + (N - firstIdx - 1);

        else
            return N + (N - firstIdx);
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